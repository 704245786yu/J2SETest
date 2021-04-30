package DesignPattern.Decorate;

/**综合咖啡（另一种咖啡名称）*/
public class HouseBlend extends Beverage{
    public HouseBlend() {
        description = "House Blend Coffee 综合咖啡";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
