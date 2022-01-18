package game;

public enum Levels {
     level1(84), level2(174), level3(276), level4(388), level5(512), level6(650), level7(801), level8(969), level9(1154), level10(1358),
    level11(1584), level12(1833), level13(2107), level14(2411), level15(2746), level16(3115), level17(3523), level18(3973), level19(4470), level20(5018);

    public final int rank;

    Levels(int rank) {
        this.rank = rank;
    }
}
