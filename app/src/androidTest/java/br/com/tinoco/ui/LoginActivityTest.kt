package br.com.tinoco.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import br.com.tinoco.R
import br.com.tinoco.api.UserApiClient
import br.com.tinoco.models.User
import br.com.tinoco.models.request.LoginRequest
import br.com.tinoco.models.response.LoginResponse
import br.com.tinoco.ui.login.LoginActivity
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @Rule
    @JvmField
    val activity = IntentsTestRule<LoginActivity>(LoginActivity::class.java)


    @Mock
    private lateinit var api: UserApiClient

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun testLoginActivityRequiredComponents() {
        onView(withId(R.id.edtEmail))
                .check(matches(isDisplayed()))

        onView(withId(R.id.btnSignup))
                .check(matches(isDisplayed()))
    }

    @Test
    fun testLoginActivityWhenEmailIsEmpty() {
        onView(withId(R.id.edtEmail)).perform(click())
                .check(matches(hasFocus()))
                .perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.btnSignup)).perform(click())
        onView(withId(R.id.edtEmail)).check(matches(hasErrorText(activity.activity.getString(R.string.invalid_email))))
    }

    @Test
    fun testLoginActivityShouldErrorEdtEmailInvalid() {
        onView(withId(R.id.edtEmail))
                .perform(click())
                .check(matches(hasFocus()))
                .perform(typeText("teste@error"), closeSoftKeyboard())

        onView(withId(R.id.btnSignup)).perform(click())

        onView(withId(R.id.edtEmail)).check(matches(hasErrorText(activity.activity.getString(R.string.invalid_email))))
    }

    @Test
    fun testLoginActivityShouldHomeEmailCorrect() {
        val expectedUser = LoginResponse(User("", "", Date(), Date(), "teste@mail.com"))

        given(api.login(LoginRequest("teste@mail.com")))
                .willReturn(Observable.just(expectedUser))

        onView(withId(R.id.edtEmail))
                .perform(click())
                .check(matches(hasFocus()))
                .perform(typeText(expectedUser.response.email), closeSoftKeyboard())

        onView(withId(R.id.btnSignup))
                .perform(click())

    }
}

