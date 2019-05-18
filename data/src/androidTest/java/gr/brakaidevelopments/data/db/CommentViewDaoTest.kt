package gr.brakaidevelopments.data.db

import android.content.Context
import android.net.Uri
import androidx.test.core.app.ApplicationProvider
import gr.brakaidevelopments.data.databaseModule
import gr.brakaidevelopments.data.model.*
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
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

class CommentViewDaoTest : KoinTest {
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
    fun insertCommentsWithParentCommentThenRetrieveThemByParentId() {
        runBlocking {
            database.commentDao().insertItem(parentComment)
            database.commentDao().insertItems(
                CommentEntity(user = user, message = "First-comment", createdAt = Date(), parentID = parentComment.id),
                CommentEntity(user = user, message = "Second-comment", createdAt = Date(), parentID = parentComment.id),
                CommentEntity(user = user, message = "Third-comment", createdAt = Date(), parentID = parentComment.id),
                CommentEntity(user = user, message = "Forth-comment", createdAt = Date(), parentID = parentComment.id),
                CommentEntity(user = user, message = "Fifth-comment", createdAt = Date(), parentID = UUID.randomUUID()),
                CommentEntity(user = user, message = "Sixth-comment", createdAt = Date(), parentID = UUID.randomUUID())
            )

            val itemView = database.commentViewDao().getCommentsByParentId(parentComment.id)

            assertThat(itemView.size, `is`(4))

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
            status = UserProfileState.ACTIVE, profileImage = Uri.parse("https://via.placeholder.com/300/09f/fff.png")
        )

        val parentComment =
            CommentEntity(user = user, message = "Parent comment", createdAt = Date(), parentID = UUID.randomUUID())

    }
}