package ro.mihai.position;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface PositionRepository extends JpaRepository<Position, Integer>,
    QuerydslPredicateExecutor<Position> {
}
