package br.com.tinoco.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.runner.AndroidJUnit4
import br.com.tinoco.R
import br.com.tinoco.api.UserApiClient
import br.com.tinoco.models.User
import br.com.tinoco.models.request.LoginRequest
import br.com.tinoco.models.response.CategoryResponse
import br.com.tinoco.models.response.LoginResponse
import br.com.tinoco.ui.home.HomeActivity
import br.com.tinoco.ui.home.HomeContract
import br.com.tinoco.ui.home.HomeFragment
import br.com.tinoco.ui.home.HomePresenter
import br.com.tinoco.util.replaceFragmentInActivity
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.*


@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    val HUSK_CATEGORY = "husk"
    private val expectedFeed = CategoryResponse("husky", mutableListOf("a", "b", "c"))
    @Mock private lateinit var view : HomeContract.View


    @Rule
    @JvmField
    val activity = IntentsTestRule<HomeActivity>(HomeActivity::class.java)

    @Mock
    private lateinit var api: UserApiClient
    var expectedResponseUser = LoginResponse(User("", "", Date(), Date(), "teste@mail.com"))



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val homeFragment = activity.activity.supportFragmentManager.findFragmentById(R.id.contentFrame)
                as HomeFragment? ?: HomeFragment.newInstance().also {
            //replaceFragmentInActivity(it, R.id.contentFrame)
        }
        activity.activity.presenter = HomePresenter(homeFragment)
    }

    @Test
    fun testBillingFragmentAlreadyAttached() {
        activity.activity.recreate()

        val actualFragment = activity.activity
                .supportFragmentManager
                .findFragmentByTag("TESTE")

        assertThat(actualFragment?.tag, `is`("TESTE"))
    }

    @Test
    fun testHomeActivityRequiredComponents() {
        Espresso.onView(ViewMatchers.withId(R.id.nav_view))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.rcvDogs))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testHome_shouldShowItems_whenResponseIsSuccess() {

        given(api.login(LoginRequest("gustavotinocoo@gmail.com"))).willReturn(
            io.reactivex.Observable.just(expectedResponseUser))


        given(api.dogs(anyString(), anyString())).willReturn(
            io.reactivex.Observable.just(expectedFeed))


        activity.activity.presenter.loadFeed(HUSK_CATEGORY)
        verify(view).showLoading(true)
        verify(view).showSuccess(HUSK_CATEGORY, expectedFeed.list)
        verify(view).showLoading(false)
    }


}

