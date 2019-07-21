package murraco.repository;

import murraco.model.CodeType;
import murraco.model.VerifyCode;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface VerifyCodeRepository extends JpaRepository<VerifyCode, Integer> {

  VerifyCode findByUserIdAndCodeType(Integer userid, CodeType codeType);


}
