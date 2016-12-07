package canma.dmml.viterbi;

/**
 *
 *Created by macan on 2016/12/7.
 * 实现李航书中viterbi 预测HMM 隐含状态的过程
 */


public class Viterbi {
    /**
     * 转移矩阵
     */
    private double[][] A;
    /**
     * 转换矩阵
     */
    private double[][] B;

    /**
     * 初始概率
     */
    private double[] pi;

    /**
     * 观测序列
     */
    private int[] observer;
    //记录每次的delta
    double[][]delta;
    //记录大 phi
    int[][] phi;
    /**
     * 隐含层序列
     */
    private int[] hidden;

    /**
     * 构造方法，
     * @param A
     * @param B
     * @param pi
     * @param observer
     */
    public Viterbi(double[][] A, double[][] B, double[] pi, int[] observer){
        this.A = A;
        this.B = B;
        this.pi = pi;
        this.observer = observer;
        hidden = new  int[observer.length];
        delta = new double[observer.length][pi.length];
         //记录最大路径
        phi = new int[observer.length][pi.length];
        //calc
        calcI();
    }

    /**
     *  ues viterbi algorithm calc hidden sequence
     */
    public void calcI(){
        //获取时间序列 序列总数
        int time = observer.length;

        //隐含层的状态数量
        int count = pi.length;


        //计算初始状态的概率
        double[] p = new double[count];
        for (int i = 0; i < count; ++i){
            //第一个时刻，观测到o1 并处于状态 i 的概率
            //p[i] = B[i][observer[0]] * pi[i];
            delta[0][i] = B[i][observer[0]] * pi[i];

            phi[0][i] = 0;
        }

        //计算每一个时刻，每种状态的的概率
        for (int t = 1; t < time; ++t){  //每一个时刻
            for (int i = 0; i < count; ++i){  //每种状态
                double[] temp = new double[count];
                for (int j =0; j < count; ++j){
                    temp[j] = delta[t - 1][j] * A[j][i] * B[i][observer[t]];
                }
                delta[t][i] = maxValue(temp);

                //求大phi
                for (int k = 0; k < count; ++k){
                    temp[k] = delta[t - 1][k] * A[k][i];
                }
                phi[t][i] = maxIndex(temp);
            }


        }
    }


    public double[][] getA() {
        return A;
    }

    public void setA(double[][] a) {
        A = a;
    }

    public double[][] getB() {
        return B;
    }

    public void setB(double[][] b) {
        B = b;
    }

    public double[] getPi() {
        return pi;
    }

    public void setPi(double[] pi) {
        this.pi = pi;
    }

    public int[] getObserver() {
        return observer;
    }

    public void setObserver(int[] observer) {
        this.observer = observer;
    }

    public int[] getHidden() {
        hidden[hidden.length - 1] = maxValue(phi[phi.length - 1]);
        for (int i = hidden.length - 1; i > 0; --i){
            hidden[i - 1] = phi[i][hidden[i]];
        }
        return hidden;
    }

    public void setHidden(int[] hidden) {
        this.hidden = hidden;
    }

    /**
     * 在数组中查找最大元素，并返回所在的下标
     * @param data 传入的数组
     * @return 最大元素的下标
     */
    public static int maxIndex(double[] data){
        if (data.length < 0){
            System.out.println("data error. params not be null");
            System.exit(-1);
        }
        int index = 0;
        double temp = data[index];

        for (int i = 0; i< data.length; ++i){
            if (data[i] > temp){
                index = i;
                temp = data[index];
            }
        }
        return index;
    }

    public static double maxValue(double[] data){
        if (data.length < 0){
            System.out.println("data error. params should not be null");
            System.exit(-1);
        }
        int index = 0;
        double temp = data[index];

        for (int i = 0; i< data.length; ++i){
            if (data[i] > temp){
                index = i;
                temp = data[index];
            }
        }
        return temp;
    }


    public static int maxValue(int[] data){
        if (data.length < 0){
            System.out.println("data error. params should not be null");
            System.exit(-1);
        }
        int index = 0;
        int temp = data[index];

        for (int i = 0; i< data.length; ++i){
            if (data[i] > temp){
                index = i;
                temp = data[index];
            }
        }
        return temp;
    }
}
