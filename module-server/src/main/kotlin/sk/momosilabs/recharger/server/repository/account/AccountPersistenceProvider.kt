package sk.momosilabs.recharger.server.repository.account

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import sk.momosilabs.recharger.server.service.account.AccountPersistence
import sk.momosilabs.recharger.server.service.account.currentUser.CurrentGoogleUser

@Repository
open class AccountPersistenceProvider(
    private val accountRepository: AccountRepository,
) : AccountPersistence {

    @Transactional
    override fun create(user: CurrentGoogleUser) =
        accountRepository.save(user.toEntity()).id

    @Transactional(readOnly = true)
    override fun existsBy(provider: String, identifierWithinProvider: String) =
        accountRepository.existsByProviderAndProviderIdentifier(provider = provider, identifierWithinProvider)

}
