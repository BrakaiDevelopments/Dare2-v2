package gr.brakaidevelopments.data.db

import android.content.Context
import android.net.Uri
import androidx.test.core.app.ApplicationProvider
import gr.brakaidevelopments.data.databaseModule
import gr.brakaidevelopments.data.model.ChallengeEntity
import gr.brakaidevelopments.data.model.CommentEntity
import gr.brakaidevelopments.data.model.LeaderBoardEntity
import gr.brakaidevelopments.data.model.UserEntity
import gr.brakaidevelopments.domain.models.ChallengeState
import gr.brakaidevelopments.domain.models.Country
import gr.brakaidevelopments.domain.models.Location
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
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

class CommentDaoTest : KoinTest {
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
    fun insertCommentAndGetByID() {
        runBlocking {
            database.challengeDao().insertItem(challenge)
            database.commentDao().insertItem(comment)

            val item = database.commentDao().getCommentByID(comment.id)

            assertThat(item, notNullValue())
            assertThat(item?.message, `is`(comment.message))
        }
    }

    @Test
    fun insertCommentAndGetByMessage() {
        runBlocking {
            database.challengeDao().insertItem(challenge)
            database.commentDao().insertItem(comment)

            val item = database.commentDao().getCommentsByMessage("%Test%")

            assertThat(item.size, `is`(1))
            assertThat(item[0].message, `is`(comment.message))
        }
    }

    @Test
    fun insertCommentAndGetByUserID() {
        runBlocking {
            database.challengeDao().insertItem(challenge)
            database.commentDao().insertItem(comment)

            val item = database.commentDao().getCommentsByUserID(user.id)

            assertThat(item.size, `is`(1))
            assertThat(item[0].message, `is`(comment.message))

            val item2 = database.commentDao().getCommentsByUserID(UUID.randomUUID())

            assertThat(item2.size, `is`(0))
        }
    }

    companion object {
        val leaderBoard = LeaderBoardEntity()

        val challenge = ChallengeEntity(
            title = "Test",
            subTitle = "Test",
            description = "TEST",
            coverImage = Uri.parse("https://via.placeholder.com/300/09f/fff.png"),
            challengeState = ChallengeState.APPROVED,
            location = Location("Greece", "Attica", "Athens", "Litous 9")
        )

        val user = UserEntity(
            username = "Test",
            password = "Test",
            email = "Test@test.com",
            birthday = Date(),
            country = Country(
                countryCode = "GR",
                countryName = "Greece"
            ),
            leaderBoardId = leaderBoard.id,
            userProfileState = UserProfileState.ACTIVE, profileImage = Uri.parse("https://via.placeholder.com/300/09f/fff.png")
        )

        val comment =
            CommentEntity(user = user, message = "Test-comment", createdAt = Date(), parentID = UUID.randomUUID(), challengeID = challenge.id)
    }

}