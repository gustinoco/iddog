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
import br.com.tinoco.ui.home.HomeFragment
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*


@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    private val expectedFeed = CategoryResponse("husky", mutableListOf("a", "b", "c"))

    @Rule
    @JvmField
    val activity = IntentsTestRule<HomeActivity>(HomeActivity::class.java)

    @Mock
    private lateinit var api: UserApiClient
    var expectedResponseUser = LoginResponse(User("", "", Date(), Date(), "teste@mail.com"))


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testBillingFragmentAlreadyAttached() {

        val actualFragment = activity.activity
                .supportFragmentManager
                .findFragmentByTag(HomeFragment::class.java.name)

        assertThat(actualFragment?.tag, `is`(HomeFragment::class.java.name))
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
    }


}

