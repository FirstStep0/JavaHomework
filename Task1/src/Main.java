public class Main {
    public static void main(String[] args) throws Exception {
        Portal news_portal = new Portal();
        news_portal.Registration("Writer", "Password");
        news_portal.Registration("Admin", "Password");
        news_portal.AddModerator("Admin");

        Portal.User writer = news_portal.LogIn("Writer", "Password");
        Portal.User admin = news_portal.LogIn("Admin", "Password");

        //add news
        News news = new News();
        news.SetTitle("Нашествие обезьян");
        news.SetText("Неизвестный вирус породил умных обезьян");
        Portal.GuestPage page = writer.PublishNews(news);

        //add comment
        Portal.CommentedPage commentedPage = news_portal.GetCommentedPage(admin, page);
        commentedPage.AddComment(admin, "Фейк");
        commentedPage.AddComment(writer, "Это правда!");
        System.out.println(page);

        //edit news
        Portal.ModeratedPage mPage = news_portal.GetModeratedPage(admin, page);
        mPage.GetNews().SetText("Неизвестный вирус не порождал умных обезьян");

        //delete comment
        mPage.DeleteComments(0);
        System.out.println(page);

        //access guest
        Portal.Guest guest = news_portal.LogInAsGuest();
        Portal.GuestPage guestPage = guest.GetPage(0);
        System.out.println(guestPage);
    }
}