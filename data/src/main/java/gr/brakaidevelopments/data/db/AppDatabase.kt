package gr.brakaidevelopments.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import gr.brakaidevelopments.data.model.ChallengeEntity
import gr.brakaidevelopments.data.model.CommentEntity
import gr.brakaidevelopments.data.model.LeaderBoardEntity
import gr.brakaidevelopments.data.model.UserEntity
import gr.brakaidevelopments.data.utils.RoomConverters

@Database(
    entities = [(UserEntity::class), (LeaderBoardEntity::class), (CommentEntity::class), (ChallengeEntity::class)],
    version = 1,
    exportSchema = true
)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun leaderBoardDao(): LeaderBoardDao
    abstract fun commentDao(): CommentDao
    abstract fun commentViewDao(): CommentViewDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "dareto.db"
            ).fallbackToDestructiveMigration().build()

        fun getInMemoryDatabase(context: Context) =
            Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    }
}