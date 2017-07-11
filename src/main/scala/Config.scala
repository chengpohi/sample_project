import com.typesafe.config.ConfigFactory

object Config {
  private val config = ConfigFactory.load()
  val ALLOWED_ORIGIN: String = config.getString("cors.allowed-origin")
  val LISTEN_PORT: Int = config.getInt("http.port")
  val BIND_IP: String = config.getString("http.interface")
}
