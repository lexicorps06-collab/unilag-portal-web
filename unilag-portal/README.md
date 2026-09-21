# University Student Portal — Login & Dashboard

A Java/Spring Boot rebuild of a university's login page and post-login
student dashboard. Built for a major project — no external database
required to demo it.

## Tech stack
- Java 17
- Spring Boot 3.3 (Spring MVC + Thymeleaf)
- Maven
- Plain CSS (no frontend framework, so it's easy to explain line-by-line)

## Project structure
```
unilag-portal/
├── pom.xml
└── src/main/
    ├── java/com/unilag/portal/
    │   ├── PortalApplication.java        # entry point
    │   ├── controller/
    │   │   ├── LoginController.java      # /login (GET+POST), /logout
    │   │   └── DashboardController.java  # /dashboard
    │   ├── model/
    │   │   ├── Student.java              # profile + academic data
    │   │   └── Course.java               # one course + grade
    │   └── service/
    │       └── AuthService.java          # mock "database": in-memory students + login check
    └── resources/
        ├── application.properties
        ├── templates/
        │   ├── login.html
        │   └── dashboard.html
        └── static/css/style.css
```

## How to run
1. Install **Java 17+** and **Maven** if you don't have them.
2. Open a terminal in the `unilag-portal` folder.
3. Run:
   ```
   mvn spring-boot:run
   ```
4. Open **http://localhost:8080/login** in your browser.

### Demo login credentials
| Matric No  | Password    |
|------------|-------------|
| 190805123  | portal123   |
| 180705089  | unilag2026  |

(These live in `AuthService.java` — add, remove, or edit students there.)

## How it works (for your defense/report)
1. **Login flow**: `GET /login` renders the login form. `POST /login`
   sends the matric number + password to `AuthService.authenticate()`,
   which checks them against the in-memory student map. On success, the
   student's matric number is stored in the **HTTP session**
   (`HttpSession`) — this is what "remembers" that the user is logged in.
2. **Session protection**: `DashboardController` checks for that session
   attribute before rendering the dashboard. No session → redirected
   back to `/login`. This is a simple form of the pattern real apps use
   (though production apps would use Spring Security instead of a
   hand-rolled check).
3. **Dashboard data**: `Course` and `Student` are plain Java model
   classes. Thymeleaf loops over `student.courses` server-side to build
   the results table — no JavaScript needed for that part.
4. **Logout**: `GET /logout` invalidates the session and redirects to
   the login page.

## Next steps if you want to extend it
- **Real database**: swap `AuthService`'s in-memory map for a
  `StudentRepository` using Spring Data JPA + MySQL, and hash passwords
  with BCrypt (`spring-boot-starter-security` provides this).
- **Registration page**: add a `POST /register` endpoint + form.
- **Admin view**: add a second role/dashboard for staff to manage
  student records.
- **Validation**: add `@Valid` + error messages on the login form for
  empty fields, etc.

## Note on branding
This uses an **original navy/teal design**, not the university's actual
logo or exact color scheme — safe to present as your own project work.
Swap the crest, colors in `style.css` (`:root` variables), and page
titles freely to match your school's actual identity if your lecturer
wants a closer visual match.
