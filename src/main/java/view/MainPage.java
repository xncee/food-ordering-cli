package view;


import model.Promo;

public class MainPage {
    private CustomerLoginPage customerLoginPage;
    private CustomerPanel customerPanel;
    public MainPage() {
        customerLoginPage();
        customerPanelPage();
    }

    public void customerLoginPage() {
        customerLoginPage = new CustomerLoginPage();
        customerLoginPage.show();
    }

    public void customerPanelPage() {
        customerPanel = new CustomerPanel(customerLoginPage.currentCustomer);
        customerPanel.show();
    }
}
