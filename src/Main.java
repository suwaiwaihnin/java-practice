import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User("佐藤美咲", LocalDate.of(1990, 1, 1)),
                new User("鈴木太郎", LocalDate.of(1991, 2, 2)),
                new User("山田一郎", LocalDate.of(2003, 3, 3)),
                new User("鈴木花子", LocalDate.of(2002, 4, 4)));
        
        System.out.println("【すべてのユーザーを表示する】");
        users.forEach(u -> System.out.printf("名前: %s, 生年月日: %s\n", u.getName(), u.getBirthdate()));

        String dateFormate = "yyyy年M月d日(E)";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormate, Locale.JAPAN);
        System.out.println("【すべてのユーザーを表示する。ただし生年月日はyyyy/MM/dd(E)形式で出力する】");
        users.forEach(u -> System.out.printf("名前: %s, 生年月日: %s\n",
                u.getName(), dateFormatter.format(u.getBirthdate())));
        
        System.out.println("【名前が鈴木で始まる人のみを表示する】");
        List<User> result1 = users.stream()
                .filter(u -> u.getName().startsWith("鈴木"))
                .collect(Collectors.toList());
        result1.forEach(p -> System.out.println(p.getName()));


    }
}
