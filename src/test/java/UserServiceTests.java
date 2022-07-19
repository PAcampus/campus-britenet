import org.junit.jupiter.api.*;
import pl.britenet.campus.database.DatabaseService;
import pl.britenet.campus.services.UserService;

public class UserServiceTests {
    private DatabaseService databaseService;
    private UserService userService;

    @BeforeEach
    public void setupService() {
        this.databaseService = new DatabaseService();
        this.userService = new UserService(this.databaseService);
    }

    @Test
    @DisplayName("Tests if retrieves users")
    public void GetUsersTest() {
        assert !this.userService.getUsers().isEmpty();
    }

    @Test
    @DisplayName("Tests if retrieves a single user")
    public void GetUserTest() {
        assert this.userService.getUser(4).isPresent();
    }
}
