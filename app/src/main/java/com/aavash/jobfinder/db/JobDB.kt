import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aavash.jobfinder.dao.JobDAO
import com.aavash.jobfinder.entity.Applied
import com.aavash.jobfinder.entity.Job

@Database(
        entities = [(Applied::class)],
        version = 1,
        exportSchema = false
)
abstract class JobDB: RoomDatabase(){
    abstract fun getJobDAO(): JobDAO
    companion object{
        @Volatile
        private var instance:JobDB?=null

        fun getInstance(context: Context):JobDB{
            if (instance==null){
                synchronized(JobDB::class){
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }
        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        JobDB::class.java,
                        "JobDB"
                ).build()
    }

}