package demos.springdata.advanced.dao;

import demos.springdata.advanced.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
    // works as findById but does not return Optional , but Label, what we need
    Label findOneById(Long id);
}
