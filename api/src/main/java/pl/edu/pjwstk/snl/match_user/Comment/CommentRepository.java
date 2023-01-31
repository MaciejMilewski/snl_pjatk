package pl.edu.pjwstk.snl.match_user.Comment;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pjwstk.snl.match.Match;
import pl.edu.pjwstk.snl.security.User.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByMatchUser_User(User user);
    List<Comment> findAllByMatchUser_Match(Match match);
}
