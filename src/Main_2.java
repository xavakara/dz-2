public class Main {

    // задание полей
    static float apartmentPrice = 1_000_000; // стоимость квартиры
    static int account = 250_000; // счЄт пользовател€
    static float wage = 100_000; // заработна€ плата в мес€ц
    static int percentFree = 50; // дол€ заработной платы на любые траты
    static float percentBank = 5; // годова€ процентна€ ставка за ипотеку
    static float[] monthlyPayments = new float[120]; // создание массива ежемес€чных платежей на 10 лет


    // метод подсчЄта стоимости квартиры с учЄтом первоначального взноса
    static private float apartmentPriceWithContribution() {
        return apartmentPrice - account; // возврат подсчитанного значени€
    }

    // метод подсчЄта ежемес€чных трат на ипотеку (зар.плата, процент своб.трат)
    static public float mortgageCosts(float amount, int percent) {
        return (amount*percent)/100;
    }

    // метод подсчЄта времени выплаты ипотеки (сумма долга, сумма платежа, годовой процент)
    // и заполнение массива monthlyPayments[] ежемес€чными платежами
    static public int countMonth(float total, float mortgageCosts, float percentBankYear) {

        float percentBankMonth = percentBankYear / 12; // подсчЄт ежемес€чного процента банка за ипотеку
        int count = 0; // счЄтчик мес€цев выплаты ипотеки

        // алгоритм расчЄта ипотеки
        while (total>0) {
            count++; // добавление нового мес€ца платежа
            total = (total + (total*percentBankMonth)/100) - mortgageCosts; // вычисление долга с учЄтом выплаты и процента
            // заполнение массива ежемес€чными платежами за ипотеку
            if(total > mortgageCosts) { // если сумма долга больше ежемес€чного платежа, то
                monthlyPayments[count-1] = mortgageCosts; // в массив добавл€етс€ целый платЄж
            } else { // иначе
                monthlyPayments[count-1] = total; // в массив добавл€етс€ платЄж равный остатку долга
            }
        }

        return count;
    }

    public static void main(String[] args) {

        // запонение экрана
        // 1) вывод количества мес€цев выплаты ипотеки
        System.out.println("»потека будет выплачиватьс€ " + countMonth(apartmentPriceWithContribution(), mortgageCosts(wage, percentFree),percentBank) + " мес€цев");
        // 2) подготовка выписки
        String monthlyPaymentsList = "";
        for(float list : monthlyPayments) {
            if (list > 0) {
                monthlyPaymentsList = monthlyPaymentsList + Float.toString(list) + " монет ";
            } else {
                break;
            }
        }
        // 3) вывод выписки ежемес€чных выплат по ипотеке
        System.out.println("ѕервоначальный взнос " + account + " монет, ежемес€чные выплаты: " + monthlyPaymentsList);
    }

}