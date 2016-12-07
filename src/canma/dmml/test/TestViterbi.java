package canma.dmml.test;

import canma.dmml.viterbi.Viterbi;
import org.junit.Test;

/**
 * Created by macan on 2016/12/7.
 */
public class TestViterbi {

    @Test
    public void  testViteribi(){
        double[][] A = {{0.5, 0.2, 0.3},
                        {0.3, 0.5, 0.2},
                        {0.2, 0.3, 0.5}};

        double[][] B = {{0.5, 0.5},
                        {0.4, 0.6},
                        {0.7, 0.3}};

        double[] pi = {0.2, 0.4, 0.4};

        int[] observer = {0, 1, 0};

        Viterbi viterbi = new Viterbi(A, B, pi, observer);
        //获取隐含层状态序列
        int[] hidden = viterbi.getHidden();

        //输出
        System.out.println("最优状态序列：");
        for (int i = 0; i < hidden.length; ++i) {
            System.out.println("I*_" + i + " = "+ hidden[i]);
        }
    }
}
