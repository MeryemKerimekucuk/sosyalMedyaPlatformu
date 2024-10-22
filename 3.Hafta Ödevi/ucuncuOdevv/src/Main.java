
import java.util.*;

class SocialMediaPlatform {

    public static class Post {  // Postlarda işlem yapabilmemiz için Post Class oluşturdum.
        private String following;
        private String favorites;
        private int postCounder = 0;
        public String comment;
        private int id;
        private User author;
        private String content;
        private List<User.Comment> comments = new ArrayList<User.Comment>();



        public Post(int id, User author, String content) {
            this.id = id;
            this.author = author;
            this.content = content;
        }

        public int Id(){
            return id;
        }


        public String getContent() {
            return content;
        }


        public Integer getId() {
            return id;
        }
    }


    static class User {
        private String name;
        private int Id;
        private LinkedHashMap<Integer, Post> posts; //Kullanıcının gönderileri
        private HashSet<User> following; //Takip edilen kullanıcılar
        private TreeSet<Integer> favorites; //Beğenilen Gönderiler
        private static int postCounter = 0; //Gönderi Sayacı
        public List<Comment> comments = new ArrayList<>();
        private User.Comment Comment;


        public User(String name, int postId) {
            this.name = name;
            this.Id = Id;
            this.posts = new LinkedHashMap<Integer, Post>();
            this.following = new HashSet<>();
            this.favorites = new TreeSet<Integer>();
            this.comments = new ArrayList<>();

            Post getPost;
            posts.get(postId);


        }


        public String getName() {
            return name;
        }


        public class Comment {
            private User user;
            private String comment;


            public Comment(User user, String comment) {
                this.user = user;
                this.comment = comment;
            }
        }




        public void follow(User user) {
            following.add(user);
            System.out.println(name + ", " + user.getName() + " " + "kullanıcısını takip ediyor");

        }


        public void createPost(String content, int postId) {
            Post newPost = new Post(postId, this, content); // postId parametresini kullan
            posts.put(newPost.getId(), newPost);
            System.out.println(name + " " + "yeni bir gönderi yayınladı:" + content);
        }

        public void addCommentToPost(User user, int postId, String comment) {
            Post post = getPost(postId);
            if (post != null) {
                Comment newComment = new Comment(user, comment);
                post.comments.add(newComment);
                comments.add(Comment);
                System.out.println("Kullanıcı Yorumu : " + comment);

            } else {
                System.out.println("Gönderi bulunamadı !");
            }

        }

        public Post getPost(int postId) {
            if (posts.containsKey(postId));
            return posts.get(postId);

        }

        public void addToPostFavorites(User user, int postId) {
            Post post = user.getPost(postId);
            if (post != null) {
                favorites.add(postId);
                System.out.println(name + ", " + user.getName() + "'in gönderisini beğendi" +" "+ post.getContent());
            }

        }

        public void showFeed() {
            System.out.println("\n" + name + "'in  Ana Sayfası");

            for (User user : following) {
                for (Map.Entry<Integer, Post> entry : user.posts.entrySet()) {
                    int postId = entry.getKey();
                    Post post = entry.getValue();
                    System.out.println("Post ID: " + postId);
                    System.out.println("Yazar: " + post.author.getName());
                    System.out.println("İçerik: " + post.getContent());
                    // Yorumlar için döngü (isteğe bağlı)
                    for (User.Comment comment : post.comments) {
                        System.out.println("Yorum: " + comment.user.getName() + " - " + comment.comment);
                    }
                }
            }
        }



        public void showPosts() {
            for (Map.Entry<Integer, Post> entry : posts.entrySet()) {
                int postId = entry.getKey();
                Post post = entry.getValue();

                System.out.println("Post ID : " + post.getId());

                System.out.println("Contents : " + post.getContent());
            }
        }



        public static class Main {

            public static void main(String[] args) {


                SocialMediaPlatform.User user1 = new SocialMediaPlatform.User("Meryem", 128);
                SocialMediaPlatform.User user2 = new SocialMediaPlatform.User("Merve", 130);


                System.out.println("KULLANICI GÖNDERİLERİ : ");
                user1.createPost("Sosyal Medya Hesabıma Hoşgeldiniz :)", 128);
                user2.createPost("İlk Gönderim !", 130);

                Post post1= user1.getPost(128);
                if(post1 != null){
                    System.out.println("Gönderi içeriği : "+post1.getContent());
                }
                else{
                    System.out.println("Gönderi bulunamadı");
                }

                Post post2= user2.getPost(130);
                if(post2 != null){
                    System.out.println("Gönderi içeriği : "+post2.getContent());
                }
                else{
                    System.out.println("Gönderi bulunamadı");
                }

                System.out.println("KULLANICILARIN POSTLARI : ");

                // Postu gösterme
                user1.showPosts();
                user2.showPosts();

                //


                System.out.println("KULLANICILARIN POST YORUMU : ");
                user1.addCommentToPost(user1, 128, "Harika görünüyorsunn");


                user2.addCommentToPost(user2, 130, "Bunu birlikte yapmalıyız!");


                System.out.println("KULLANICI POSTU BEĞENME : ");
                user1.addToPostFavorites(user1, 128);
                user2.addToPostFavorites(user2, 130);

                System.out.println("KULLANICI TAKİP ETME : ");
                user1.follow(user2);
                user2.follow(user1);

                System.out.println("KULLANICI AKIŞINI GÖSTERME : ");

                user1.showFeed();


            }
        }
    }
}