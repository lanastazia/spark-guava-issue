import com.google.cloud.storage.StorageOptions
import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf

import scala.collection.JavaConverters._

object Main {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      // .setMaster(s"local[*]") // only for execution in local mode

    val sc = SparkSession.builder.config(conf).getOrCreate()

    // Print the name of each blob in the bucket
    val storage = StorageOptions.getDefaultInstance.getService

    storage
      .list("gcp-public-data-sentinel-2").getValues.asScala
      .foreach(println)

    sc.stop()
  }
}
