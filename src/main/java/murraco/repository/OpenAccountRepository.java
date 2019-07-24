package murraco.repository;

import murraco.model.OpenAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenAccountRepository extends JpaRepository<OpenAccount, Integer> {

    OpenAccount findByOpenAccountTypeAndOpenId(Integer accountType, String openId);

}
