package la.shiro.birdapi.util

import cn.dev33.satoken.secure.SaSecureUtil
import org.springframework.stereotype.Component

/**
 *  author: Rin Shiro
 *  Date: 23/4/10 11:49
 *  Description :
 */
@Component
object PasswordUtil {

    private const val PRIVATE_KEY =
        "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJcuSmcf1TXByir9OGSGZ3dR61EHHg8PyBybmc1HypvkF7064JVK4Jgm0cbyL2QMWPTfUrYC2mTEjxi/RmjmdP+OS6vAEWEV+0K7t0Uv6NZvKqndHJ/C7gYUVaoz1J5aAh4I71lUveOWhBaK6zMPv+gaYS6XrEjzTPL548PqrpGHAgMBAAECgYAWFwV4HAajXDWWInni9mCX5rhjlo6l1MCArZvDiwHn1WRCmh/GsLIwiTuNvkV+cO9umFMVlUUkBDSEgS5glYvysBh1f5Nj3w/lb7nvRZqxamR5WzY4kiAui3CN0xpOphqXepBlNprzdvulOm7ixeq9hO4/vWexGkErhiiwNVQbQQJBAJi+R4igqVOuN9EnMOG6PSZ/1drBcq0ug36L98MUjGN/Mm4DuKapZ9nV/2LvNX3PTfyWrMj02O8jdnlO3RgDkLUCQQD9YZzkAg5PWmVkfACkUXxGlcTVPcZrRnFg/JBWcORl50n/OlZH2MJDA0+rMHZ9sFmJetLECdEPb7HF+eQYfMrLAkBQtADRxKmSyDM/tChUq/VRWSviKGHx9OiPPV8MWNgQXg2EKT0v+cQgSbxlRqSJs7Wt5uKmTxAr0h3v3GZfIMsdAkA8Gr2WsI4ocK8AiRG16MZsD/bjBoZcyBb5BLBQcPMc4SKCcpoo7NgYAV8SoqpY4k46+HGqoxF+ceZfqglbba7FAkBsULX0IcaJYW+JkZh9Cg5UNFbMamBEmOi2SeuuNG10Uqr/rxrw4STArXAFC2e2GsFS5874ir+2j2Nxu4P6WDqB"
    private const val PUBLIC_KEY =
        "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXLkpnH9U1wcoq/Thkhmd3UetRBx4PD8gcm5nNR8qb5Be9OuCVSuCYJtHG8i9kDFj031K2AtpkxI8Yv0Zo5nT/jkurwBFhFftCu7dFL+jWbyqp3Ryfwu4GFFWqM9SeWgIeCO9ZVL3jloQWiuszD7/oGmEul6xI80zy+ePD6q6RhwIDAQAB"

    fun encode(password: String): String {
        return SaSecureUtil.rsaEncryptByPublic(PUBLIC_KEY, password)
    }

    fun decode(password: String): String {
        return SaSecureUtil.rsaDecryptByPrivate(PRIVATE_KEY, password)
    }

    fun match(password: String, encodedPassword: String): Boolean {
        return SaSecureUtil.rsaDecryptByPrivate(PRIVATE_KEY, encodedPassword) == password
    }
}