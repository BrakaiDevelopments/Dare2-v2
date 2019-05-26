package gr.brakaidevelopments.data.db

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.net.Uri
import androidx.test.core.app.ApplicationProvider
import gr.brakaidevelopments.data.databaseModule
import gr.brakaidevelopments.data.model.LeaderBoardEntity
import gr.brakaidevelopments.data.model.UserEntity
import gr.brakaidevelopments.domain.models.CountryEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.inject
import java.util.*

class UserDaoTest : KoinTest {
    private val database by inject<AppDatabase>(named("Test"))
    private val context: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        startKoin {
            androidContext(context)
            modules(databaseModule)
        }
    }

    @After
    fun tearDown() {
        database.close()
        stopKoin()
    }

    @Test(expected = SQLiteConstraintException::class)
    fun insertUserWithoutLeaderBoard() {
        runBlocking {
            database.userDao().insertItem(user)
        }
    }

    @Test
    fun insertUserThenGetById() {
        runBlocking {
            database.leaderBoardDao().insertItem(leaderBoard)
            database.userDao().insertItem(user)

            val item = database.userDao().getUserByID(user.id)

            assertThat(item, notNullValue())
            assertThat(item?.id, `is`(user.id))

        }
    }

    @Test
    fun insertUserThenGetByInvalidId() {
        runBlocking {
            database.leaderBoardDao().insertItem(leaderBoard)
            database.userDao().insertItem(user)

            val item = database.userDao().getUserByID(UUID.randomUUID())

            assertThat(item, nullValue())
        }
    }


    @Test
    fun insertUserThenGetByEmail() {
        runBlocking {
            database.leaderBoardDao().insertItem(leaderBoard)
            database.userDao().insertItem(user)

            val item = database.userDao().getUserByEmail(user.email)

            assertThat(item, notNullValue())
            assertThat(item?.email, `is`(user.email))
        }
    }

    @Test
    fun insertUserThenGetByUsername() {
        runBlocking {
            database.leaderBoardDao().insertItem(leaderBoard)
            database.userDao().insertItem(user)

            val item = database.userDao().getUserByUsername(user.username)

            assertThat(item, notNullValue())
            assertThat(item?.username, `is`(user.username))
        }
    }

    @Test
    fun insertUserThenGetProfileImage() {
        runBlocking {
            database.leaderBoardDao().insertItem(leaderBoard)
            database.userDao().insertItem(user)

            val item = database.userDao().getUserProfileImage(user.id)

            assertThat(item, notNullValue())
        }
    }

    @Test
    fun insertUserThenGetUserStatus() {
        runBlocking {
            database.leaderBoardDao().insertItem(leaderBoard)
            database.userDao().insertItem(user)

            val item = database.userDao().getUserStatus(user.id)

            assertThat(item, notNullValue())
            assertThat(item, `is`(UserProfileState.ACTIVE))
        }
    }

    @Test
    fun insertUsersThenGetByCountryName() {
        runBlocking {
            database.leaderBoardDao().insertItem(leaderBoard)
            database.userDao().insertItems(
                user,
                user.copy(
                    id = UUID.randomUUID(),
                    username = "1",
                    email = "1@1.com",
                    country = CountryEntity(
                        countryCode = "GR",
                        countryName = "Greece"
                    )
                ),
                user.copy(
                    id = UUID.randomUUID(),
                    username = "2",
                    email = "2@1.com",
                    country = CountryEntity(
                        countryCode = "GR",
                        countryName = "Greece"
                    )
                ),
                user.copy(
                    id = UUID.randomUUID(),
                    username = "3",
                    email = "3@1.com",
                    country = CountryEntity(
                        countryCode = "AL",
                        countryName = "Albania"
                    )
                )
            )

            val res1 = database.userDao().getUserByCountryName("Greece")
            assertThat(res1.size, `is`(3))

            val res2 = database.userDao().getUserByCountryName("Albania")
            assertThat(res2.size, `is`(1))

        }
    }

    @Test
    fun insertUserThenGetUserCredentials() {
        runBlocking {
            database.leaderBoardDao().insertItem(leaderBoard)
            database.userDao().insertItem(user)

            val item = database.userDao().getUserCredentialsByID(user.id)

            assertThat(item, notNullValue())
            assertThat(item?.username, `is`("Test"))
        }
    }


    companion object {
        val leaderBoard = LeaderBoardEntity()


        val user = UserEntity(
            username = "Test",
            password = "Test",
            email = "Test@test.com",
            birthday = Date(),
            country = CountryEntity(
                countryCode = "GR",
                countryName = "Greece"
            ),
            leaderBoardId = leaderBoard.id,
            userProfileState = UserProfileState.ACTIVE, profileImage = Uri.parse("https://via.placeholder.com/300/09f/fff.png")
        )
    }
}