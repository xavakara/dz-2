public class Main {

    // ������� �����
    static float apartmentPrice = 1_000_000; // ��������� ��������
    static int account = 250_000; // ���� ������������
    static float wage = 100_000; // ���������� ����� � �����
    static int percentFree = 50; // ���� ���������� ����� �� ����� �����
    static float percentBank = 5; // ������� ���������� ������ �� �������
    static float[] monthlyPayments = new float[120]; // �������� ������� ����������� �������� �� 10 ���


    // ����� �������� ��������� �������� � ������ ��������������� ������
    static private float apartmentPriceWithContribution() {
        return apartmentPrice - account; // ������� ������������� ��������
    }

    // ����� �������� ����������� ���� �� ������� (���.�����, ������� ����.����)
    static public float mortgageCosts(float amount, int percent) {
        return (amount*percent)/100;
    }

    // ����� �������� ������� ������� ������� (����� �����, ����� �������, ������� �������)
    // � ���������� ������� monthlyPayments[] ������������ ���������
    static public int countMonth(float total, float mortgageCosts, float percentBankYear) {

        float percentBankMonth = percentBankYear / 12; // ������� ������������ �������� ����� �� �������
        int count = 0; // ������� ������� ������� �������

        // �������� ������� �������
        while (total>0) {
            count++; // ���������� ������ ������ �������
            total = (total + (total*percentBankMonth)/100) - mortgageCosts; // ���������� ����� � ������ ������� � ��������
            // ���������� ������� ������������ ��������� �� �������
            if(total > mortgageCosts) { // ���� ����� ����� ������ ������������ �������, ��
                monthlyPayments[count-1] = mortgageCosts; // � ������ ����������� ����� �����
            } else { // �����
                monthlyPayments[count-1] = total; // � ������ ����������� ����� ������ ������� �����
            }
        }

        return count;
    }

    public static void main(String[] args) {

        // ��������� ������
        // 1) ����� ���������� ������� ������� �������
        System.out.println("������� ����� ������������� " + countMonth(apartmentPriceWithContribution(), mortgageCosts(wage, percentFree),percentBank) + " �������");
        // 2) ���������� �������
        String monthlyPaymentsList = "";
        for(float list : monthlyPayments) {
            if (list > 0) {
                monthlyPaymentsList = monthlyPaymentsList + Float.toString(list) + " ����� ";
            } else {
                break;
            }
        }
        // 3) ����� ������� ����������� ������ �� �������
        System.out.println("�������������� ����� " + account + " �����, ����������� �������: " + monthlyPaymentsList);
    }

}