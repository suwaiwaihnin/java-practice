import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
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

        System.out.println("【生年月日が2000年1月1日以降の人のみを表示する】");
        List<User> filterByDate = users.stream().filter(user -> user.getBirthdate().isAfter(LocalDate.of(2000, 1, 1))).collect(Collectors.toList());
        filterByDate.forEach(u -> System.out.printf("名前: %s, 生年月日: %s\n",
                u.getName(), u.getBirthdate()));

        System.out.println("【生年月日の昇順に並び替えて表示する】");
        List<User> sortedUsersByDate = users.stream()
                .sorted(Comparator.comparing(u -> u.getBirthdate()))
                .collect(Collectors.toList());
        sortedUsersByDate.forEach(u -> System.out.printf("名前: %s, 生年月日: %s\n",
                u.getName(), u.getBirthdate()));

        System.out.println("【生年月日の降順に並び替えて表示する】");
        List<User> sortedUsersByDescDate = users.stream()
                .sorted(Comparator.comparing(User::getBirthdate).reversed())
                .collect(Collectors.toList());
        sortedUsersByDescDate.forEach(u -> System.out.printf("名前: %s, 生年月日: %s\n",
                u.getName(), u.getBirthdate()));

        System.out.println("【2022年7月1日時点の各ユーザーの年齢を表示する】");
        users.forEach(u -> System.out.printf("名前: %s, 年齢: %s歳\n", u.getName(), getUserAge(u.getBirthdate(), LocalDate.of(2022, 7, 1))));

        System.out.println("【2022年7月1日時点で20歳以下のユーザーを表示する】");
        List<User> userUnderAge20 = users.stream()
                .filter(user -> getUserAge(user.getBirthdate(), LocalDate.of(2022, 7, 1)) <= 20)
                .collect(Collectors.toList());
        userUnderAge20.forEach(u -> System.out.printf("名前: %s, 年齢: %s歳\n", u.getName(), getUserAge(u.getBirthdate(), LocalDate.of(2022, 7, 1))));

        System.out.println("【2022年7月1日時点で20歳未満のユーザーを表示する】");
        List<User> userAgeNot20 = users.stream()
                .filter(user -> getUserAge(user.getBirthdate(), LocalDate.of(2022, 7, 1)) < 20)
                .collect(Collectors.toList());
        userAgeNot20.forEach(u -> System.out.printf("名前: %s, 年齢: %s歳\n", u.getName(), getUserAge(u.getBirthdate(), LocalDate.of(2022, 7, 1))));


    }

    public static int getUserAge(LocalDate birthDate, LocalDate currentDate) {

        return Period.between(birthDate, currentDate).getYears();
    }
}
