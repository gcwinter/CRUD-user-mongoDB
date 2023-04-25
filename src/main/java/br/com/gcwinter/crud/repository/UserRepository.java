package br.com.gcwinter.crud.repository;


import br.com.gcwinter.crud.repository.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity,String> {
}
