package gr.brakaidevelopments.data.db

import android.content.Context
import android.net.Uri
import androidx.test.core.app.ApplicationProvider
import gr.brakaidevelopments.data.databaseModule
import gr.brakaidevelopments.data.model.CountryEntity
import gr.brakaidevelopments.data.model.LeaderBoardEntity
import gr.brakaidevelopments.data.model.UserEntity
import gr.brakaidevelopments.data.model.UserProfileState
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

class LeaderBoardDaoTest : KoinTest {
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

    @Test
    fun insertLeaderBoardThenGetByInvalidId() {
        runBlocking {

            database.leaderBoardDao().insertItem(leaderBoard)

            val item = database.leaderBoardDao().getLeaderBoardByID(UUID.randomUUID())

            assertThat(item, nullValue())
        }
    }

    @Test
    fun insertLeaderBoardThenGetById() {
        runBlocking {

            database.leaderBoardDao().insertItem(leaderBoard)

            val item = database.leaderBoardDao().getLeaderBoardByID(leaderBoard.id)

            assertThat(item, notNullValue())
            assertThat(item?.id, `is`(leaderBoard.id))
        }
    }

    @Test
    fun insertLeaderBoardThenGetUserPoints() {
        runBlocking {

            database.leaderBoardDao().insertItem(leaderBoard.apply { points = 100 })
            database.userDao().insertItem(user)

            val item = database.leaderBoardDao().getUserPoints(user.id)

            assertThat(item, notNullValue())
            assertThat(item, `is`(100L))
        }
    }

    @Test
    fun insertLeaderBoardThenGetUserParticipates() {
        runBlocking {

            database.leaderBoardDao().insertItem(leaderBoard.apply { num_of_participates = 10 })
            database.userDao().insertItem(user)

            val item = database.leaderBoardDao().getUserParticipates(user.id)

            assertThat(item, notNullValue())
            assertThat(item, `is`(10L))
        }
    }

    @Test
    fun insertLeaderBoardThenGetUserCompletedChallenges() {
        runBlocking {

            database.leaderBoardDao().insertItem(leaderBoard.apply { completed_challenges = 10 })
            database.userDao().insertItem(user)

            val item = database.leaderBoardDao().getUserCompletedChallenges(user.id)

            assertThat(item, notNullValue())
            assertThat(item, `is`(10L))
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