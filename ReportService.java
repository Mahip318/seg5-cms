public class ReportService {
  public void generateReport(OrderService orderService) {
    System.out.println("Generating Sales Report.");
    orderService.displayMenu();
  }
}
