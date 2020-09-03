package pt.ulisboa.tecnico.socialsoftware.tutor.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pt.ulisboa.tecnico.socialsoftware.tutor.tournament.domain.Tournament;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
    @Query(value = "SELECT * FROM tournaments t WHERE t.course_execution_id IN :courseExecutions", nativeQuery = true)
    List<Tournament> getAllTournamentsForCourseExecutions(Set courseExecutions);

    @Query(value = "SELECT * FROM tournaments t WHERE t.start_time < CURRENT_TIMESTAMP AND t.end_time > CURRENT_TIMESTAMP AND t.is_canceled = 'false' AND t.course_execution_id IN :courseExecutions", nativeQuery = true)
    List<Tournament> getOpenedTournamentsForCourseExecutions(Set courseExecutions);
    
    @Query(value = "SELECT * FROM tournaments t WHERE t.end_time < CURRENT_TIMESTAMP AND t.is_canceled = 'false' AND t.course_execution_id IN :courseExecutions", nativeQuery = true)
    List<Tournament> getClosedTournamentsForCourseExecutions(Set courseExecutions);
    
    @Query(value = "SELECT * FROM tournaments t WHERE t.user_id = :user_id", nativeQuery = true)
    List<Tournament> getTournamentsByUserId(Integer user_id);
    
    @Query(value = "SELECT * FROM tournaments t WHERE t.course_execution_id = :execution_id", nativeQuery = true)
    List<Tournament> getTournamentsByExecutionId(Integer execution_id);
    
    @Query(value = "SELECT t.course_execution_id FROM tournaments t WHERE t.id = :id", nativeQuery = true)
    Optional<Integer> findCourseExecutionIdByTournamentId(int id);
}
