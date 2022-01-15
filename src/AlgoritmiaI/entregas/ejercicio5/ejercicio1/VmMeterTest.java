package AlgoritmiaI.entregas.ejercicio5.ejercicio1;

import AlgoritmiaI.datastructures.binarytree.BinaryTree;
import AlgoritmiaI.entregas.ejercicio5.VmInfo;
import org.testng.annotations.Test;

import java.util.Calendar;

import static org.testng.Assert.assertEquals;

public final class VmMeterTest {
    @Test
    void testGetHighestCpuTimeVm() {
        final Calendar vm1Start = Calendar.getInstance();
        vm1Start.set(2021, 0, 4, 16, 31, 46);
        final Calendar vm1End = Calendar.getInstance();
        vm1End.set(2021, 0, 4, 16, 42, 7);
        final Calendar vm2Start = Calendar.getInstance();
        vm2Start.set(2021, 0, 14, 21, 15, 17);
        final Calendar vm2End = Calendar.getInstance();
        vm2End.set(2021, 0, 14, 21, 15, 17);
        final Calendar vm3Start = Calendar.getInstance();
        vm3Start.set(2021, 1, 17, 9, 11, 07);
        final Calendar vm3End = Calendar.getInstance();
        vm3End.set(2021, 1, 24, 11, 6, 36);
        final VmInfo vm1 = new VmInfo();
        vm1.setProcessName("Windows 10");
        vm1.setStartDate(vm1Start);
        vm1.setEndDate(vm1End);
        final VmInfo vm2 = new VmInfo();
        vm2.setProcessName("Fedora 32");
        vm2.setStartDate(vm2Start);
        vm2.setEndDate(vm2End);
        final VmInfo vm3 = new VmInfo();
        vm3.setProcessName("macOS Big Sur");
        vm3.setStartDate(vm3Start);
        vm3.setEndDate(vm3End);
        final BinaryTree<VmInfo> tree = new BinaryTree<VmInfo>();
        tree.insert(vm1);
        tree.insert(vm2);
        tree.insert(vm3);
        assertEquals(
                "macOS Big Sur",
                VmMeter.getHighestCpuTimeVm(tree).getProcessName());
    }

}
