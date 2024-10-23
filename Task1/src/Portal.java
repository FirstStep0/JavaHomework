import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Portal {
    public Dictionary<String, User> users;
    public List<String> moderators;
    public List<GuestPage> pages;

    Portal(){
        users = new Hashtable<>();
        moderators = new ArrayList<>();
        pages = new ArrayList<>();
    }

    public boolean Registration(String login, String password) {
        boolean ok = false;
        if(login != null && password != null) {
            User user = users.get(login);
            if(user == null) {
                user = new User(this, login, password);
                users.put(login, user);
                ok = true;
            }
        }
        return ok;
    }

    public User LogIn(String login, String password) {
        User user = null;
        if(login != null) {
            User possible_user = users.get(login);
            if(possible_user != null && possible_user.CheckPassword(password)) {
                user = possible_user;
            }
        }
        return user;
    }

    public Guest LogInAsGuest(){
        return new Guest(this);
    }

    public GuestPage AddNews(User user, News news) {
        GuestPage page = new GuestPage(user, news);
        boolean ok = pages.add(page);
        return ok ? page : null;
    }

    public GuestPage GetPage(int index) {
        GuestPage page = null;
        if(0 <= index && index < pages.size()) {
            page = pages.get(index);
        }
        return page;
    }

    public int PagesCount() {
        return pages.size();
    }

    protected boolean CheckModerator(User user){
        for(String login: moderators){
            if(login.equals(user.GetLogin())){
                return true;
            }
        }
        return false;
    }

    public CommentedPage GetCommentedPage(User user, GuestPage page) {
        if(user != null) {
            return new CommentedPage(page);
        }
        return null;
    }

    public EditedPage GetEditedPage(User user, GuestPage page) {
        if(page.GetOwner().GetLogin().equals(user.GetLogin()) || CheckModerator(user)){
            return new EditedPage(page);
        }
        return null;
    }

    public ModeratedPage GetModeratedPage(User user, GuestPage page) {
        if(CheckModerator(user)){
            return new ModeratedPage(page);
        }
        return null;
    }

    public void AddModerator(String login) {
        moderators.add(login);
    }

    // Pages
    class GuestPage {
        protected User owner;
        protected News news;
        protected List<String> comments = new ArrayList<String>();

        public GuestPage(User owner, News news) {
            this.owner = owner;
            this.news = news;
        }

        protected GuestPage(User owner, News news, List<String> comments) {
            this(owner, news);
            this.comments = comments;
        }

        public GuestPage(GuestPage page){
            this(page.owner, page.news, page.comments);
        }

        public User GetOwner(){
            return owner;
        }

        public News GetNews(){
            return new News(news);
        }

        public List<String> GetComments(){
            return new ArrayList<String>(comments);
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append("Автор: ").append(owner.toString()).append("\n");
            result.append(news.toString()).append("\n");
            result.append("Комментарии: " + "\n");
            for(String comment: comments){
                result.append(comment).append("\n");
            }
            return result.toString();
        }
    }

    class CommentedPage extends GuestPage {
        CommentedPage(User owner, News news){
            super(owner, news);
        }

        CommentedPage(GuestPage page){
            super(page);
        }

        public void AddComment(User user, String comment){
            comments.add(user.toString() + " пишет: " + comment);
        }
    }

    class EditedPage extends CommentedPage {
        EditedPage(User owner, News news){
            super(owner, news);
        }

        EditedPage(GuestPage page){
            super(page);
        }

        @Override
        public News GetNews() {
            return news;
        }
    }

    class ModeratedPage extends EditedPage {
        ModeratedPage(User owner, News news){
            super(owner, news);
        }

        ModeratedPage(GuestPage page){
            super(page);
        }

        @Override
        public List<String> GetComments(){
            return comments;
        }

        public boolean DeleteComments(int index){
            if(0 <= index && index < comments.size()){
                comments.remove(index);
                return true;
            }
            return false;
        }

        public boolean DeletePage(int index) {
            if(0 <= index && index < pages.size()){
                pages.remove(index);
                return true;
            }
            return false;
        }
    }

    // users
    class Guest {
        protected final Portal portal;

        Guest(Portal portal){
            this.portal = portal;
        }

        public GuestPage GetPage(int index){
            return portal.GetPage(index);
        }

        public int PagesCount(){
            return portal.PagesCount();
        }
    }

    class User extends Guest {
        private final String login;
        private String password = null;

        User(Portal portal, String login, String password){
            super(portal);
            this.login = login;
            this.password = password;
        }

        public String GetLogin(){
            return login;
        }

        public boolean CheckPassword(String checkedPassword){
            return password.equals(checkedPassword);
        }

        public boolean ChangePassword(String oldPassword, String newPassword) {
            boolean check = CheckPassword(oldPassword);
            if(check) {
                password = newPassword;
            }
            return check;
        }

        public GuestPage PublishNews(News news){
            return portal.AddNews(this, news);
        }

        @Override
        public String toString() {
            return GetLogin();
        }
    }
}
