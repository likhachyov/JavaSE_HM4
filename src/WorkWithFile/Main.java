package WorkWithFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final int topPlacesCount = 5;
    private static final String pathFile = "src\\WorkWithFile\\Data USA Cart.csv";

    public static void main(String[] args) {
        ArrayList<FileEntry> list = parseFile(pathFile);
        FileEntry[] top = defineTop(list, topPlacesCount);
        String forecast = defineForecast(top);
        System.out.println();
        System.out.printf("TOP %d:", topPlacesCount);
        System.out.println();

        for (int place = top.length - 1, count = 1; place >= 0; place--, count++) {
            System.out.print(count);
            System.out.print(' ');
            System.out.println(top[place]);
        }
        System.out.println();
        System.out.println("Forecast about an increase:");
        System.out.println(forecast);
    }

    private static ArrayList<FileEntry> parseFile(String pathFile) {
        ArrayList<FileEntry> list = new ArrayList<>();
        Scanner scan = null;
        try{
            scan = new Scanner(new File(pathFile));
        }
        catch (Exception e) {
            System.out.println(e.getCause());
        }
        if (scan != null){
            String entryString = new String();
            int count = 0;
//            Пропускаем строку с именами столбцов
            scan.nextLine();
            while (scan.hasNext()){
                count++;
                entryString = scan.nextLine();
                String[] entryFields = entryString.split(",");
//                На случай неверной записи в файле
                try {
                    list.add(new FileEntry(entryFields[0], entryFields[1], entryFields[2], entryFields[3], Integer.valueOf(entryFields[4]), Integer.valueOf(entryFields[5]), Integer.valueOf(entryFields[6]), Integer.valueOf(entryFields[7])));
                }
                catch (Exception e){
                    System.out.println(String.format("Wrong in the %d string: ",count) + entryString);
                }
            }
        }
        return list;
    }

    private static FileEntry[] defineTop(ArrayList<FileEntry> list, int placesCount) {
        FileEntry[] top = new FileEntry[placesCount];
        for (int i = 0; i < list.size(); i++) {
//            Считаем, что первые там и есть ТОП
            if (i < top.length)
                top[i] = list.get(i);
            else {
//                Затем для каждого проверяем, может ли он занят какое-нибудь место в ТОПе
                for (int j = top.length - 1; j >= 0; j--) {
//                    Если это новое место в ТОПе, прерываем сравнивание - сохраняем ТОП в порядке возрастания
                    if (list.get(i).getDelta() > top[j].getDelta()){
                        top[j] = list.get(i);
                        break;
                    }
//                    Мало ли, прирост одинаковый, тогда в ТОП попадёт тот, где меньше населения
                    else if (list.get(i).getDelta() == top[j].getDelta()){
                        top[j] = list.get(i).getPop2013() > top[j].getPop2013() ? list.get(i) : top[j];
                    }
                }
            }
        }
        return top;
    }

    private static String defineForecast(FileEntry[] top) {

        StringBuilder forecast = new StringBuilder();
        for (FileEntry entry :
                top) {
            if (entry != null){
                forecast.append(entry.getGeoID());
                forecast.append(" the increase in next year: ");
//            Среднее значение приростов за 3 года и будет оптимальным прогнозом на следующий год
                int increase = (entry.getPop2014() - entry.getPop2013() + entry.getPop2015() - entry.getPop2014() + entry.getPop2016() - entry.getPop2016()) / 3;
                forecast.append(increase);
                forecast.append(System.lineSeparator());}
        }
        return forecast.toString();
    }
}
