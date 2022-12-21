public class TemplateA extends BuildTemplate {

    @Override
    public void buildCatalog() {
        System.out.println("Building catalog");
    }

    @Override
    public void buildAddons() {
        System.out.println("Building addons");
    }

}