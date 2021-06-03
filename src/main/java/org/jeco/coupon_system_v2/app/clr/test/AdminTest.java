package org.jeco.coupon_system_v2.app.clr.test;

import lombok.RequiredArgsConstructor;
import org.jeco.coupon_system_v2.app.beans.Category;
import org.jeco.coupon_system_v2.app.beans.Company;
import org.jeco.coupon_system_v2.app.beans.Coupon;
import org.jeco.coupon_system_v2.app.beans.Customer;
import org.jeco.coupon_system_v2.app.login.ClientType;
import org.jeco.coupon_system_v2.app.login.LoginManager;
import org.jeco.coupon_system_v2.app.repository.CompanyRepository;
import org.jeco.coupon_system_v2.app.repository.CouponRepository;
import org.jeco.coupon_system_v2.app.repository.CustomerRepository;
import org.jeco.coupon_system_v2.app.service.AdminService;
import org.jeco.coupon_system_v2.app.utils.TestUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Order(3)
public class AdminTest implements CommandLineRunner {

    private final LoginManager loginManager;
    private final ApplicationContext ctx;

    @Override
    public void run(String... args) throws Exception {
       // dataLoader();
        //admin login
        TestUtils.testPrimaryInfo("admin service");
        TestUtils.testSecondaryInfo("login bad");
        AdminService adminService = null;
        try {
            adminService = (AdminService) loginManager.login("admln@admin.com", "admin", ClientType.ADMINISTRATOR);
        } catch (Exception e) {
            System.out.println(e);
        }
        TestUtils.testSecondaryEnd();

        TestUtils.testSecondaryInfo("login success");
        adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
        TestUtils.testSecondaryEnd();

        //add company bad
        TestUtils.testSecondaryInfo("add company bad");
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("\r\nadd company");
        try {
            adminService.addCompany(Company.builder()
                    .email(null)
                    .name("testCompany")
                    .password("123456")
                    .build());

        } catch (Exception e) {
            System.out.println(e);

        }
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //add company success
        TestUtils.testSecondaryInfo("add company success");
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("\r\nadd company");
        try {
            adminService.addCompany(Company.builder()
                    .email("test@company")
                    .name("testCompany")
                    .password("123456")
                    .build());

            adminService.addCompany(Company.builder()
                    .email("test@company1")
                    .name("testCompany1")
                    .password("1234567")
                    .build());

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //update company bad
        TestUtils.testSecondaryInfo("update company bad");
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("\r\nupdate company");
        try {
            Company company = adminService.getOneCompany(1);
            company.setId(50);
            adminService.updateCompany(company);

        } catch (Exception e) {
            System.out.println(e);

        }
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //update company success
        TestUtils.testSecondaryInfo("update company success");
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("\r\nupdate company");
        try {
            Company company = adminService.getOneCompany(1);
            company.setPassword("abc123");
            adminService.updateCompany(company);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        TestUtils.testSecondaryEnd();


        //delete company success
        TestUtils.testSecondaryInfo("delete company success");
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("\r\ndelete company");
        adminService.deleteCompany(1);
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //get one company by id--bad
        //TODO need response if need handle exception
        TestUtils.testSecondaryInfo("get one company bad");
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("\r\nget one company");

        try {
            adminService.getOneCompany(50);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //get one company by id--success
        TestUtils.testSecondaryInfo("get one company success ");
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("\r\nget one company");
        try {
            Company company = adminService.getOneCompany(1);
            company.setId(1);
            adminService.getOneCompany(1);
        } catch (Exception e) {
            //noinspection ThrowablePrintedToSystemOut
            System.out.println(e);
        }
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //add customer success
        TestUtils.testSecondaryInfo("add customer success");
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("\r\nadd customer");
        try {
            adminService.addCustomer(Customer.builder()
                    .email("aaa@rry")
                    .password("1234")
                    .firstName("Mu")
                    .lastName("Bu")
                    .build());

            adminService.addCustomer(Customer.builder()
                    .email("Vir@rry")
                    .password("12345")
                    .firstName("Murzik")
                    .lastName("Bur")
                    .build());

            adminService.addCustomer(Customer.builder()
                    .email("tom@rry")
                    .password("123456")
                    .firstName("Moshe")
                    .lastName("Kaz")
                    .build());

        } catch (Exception e) {
            System.out.println(e);

        }
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //add customer bad
        TestUtils.testSecondaryInfo("add customer bad");
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("\r\nadd customer");
        try {
            adminService.addCustomer(Customer.builder()
                    .email("aaa@rry")
                    .password("1234")
                    .firstName("Kut")
                    .lastName("But")
                    .build());

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //update customer bad
        TestUtils.testSecondaryInfo("update customer bad");
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("\r\nupdate customer");
        try {
            Customer customer = adminService.getOneCustomer(1);
            customer.setId(50);
            adminService.updateCustomer(customer);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //update customer success
        TestUtils.testSecondaryInfo("update customer success");
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("\r\nupdate customer");
        try {
            Customer customer = adminService.getOneCustomer(1);
            customer.setPassword("123");
            adminService.updateCustomer(customer);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

      //delete customer success
        TestUtils.testSecondaryInfo("delete customer success");
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("\r\ndelete customer");
        adminService.deleteCustomer(1);
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        TestUtils.testSecondaryEnd();


       //get one customer by id--bad
        TestUtils.testSecondaryInfo("get one customer bad");
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("\r\nget one customer");

        try {

            Customer customer = adminService.getOneCustomer(1);
            customer.setId(50);
            adminService.getOneCustomer(50);

        } catch (Exception e) {
            System.out.println(e);

        }
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //get one customer by id--success
        TestUtils.testSecondaryInfo("get one customer success ");
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        try {

            Customer customer = adminService.getOneCustomer(2);
            customer.setId(2);
            adminService.getOneCustomer(2);

        } catch (Exception e) {
            System.out.println(e);

        }
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        TestUtils.testSecondaryEnd();


    }

    private void dataLoader(){
        CompanyRepository companyRepository = ctx.getBean(CompanyRepository.class);
       CouponRepository couponRepository = ctx.getBean(CouponRepository.class);
        CustomerRepository customerRepository = ctx.getBean(CustomerRepository.class);
        System.out.println("\r\n*********\r\ndata load\r\n*********\r\n");
        //add company
        companyRepository.save(Company.builder().name("Amedei").email("Amedei@company.com").password("123456").build());
        companyRepository.save(Company.builder().name("Angel Bakeries").email("Angel.Bakeries@company.com").password("123456").build());
        companyRepository.save(Company.builder().name("Auricchio").email("Auricchio@company.com").password("123456").build());
        companyRepository.save(Company.builder().name("Autogrill").email("Autogrill@company.com").password("123456").build());
        companyRepository.save(Company.builder().name("Babayevsky").email("Babayevsky@company.com").password("123456").build());
        companyRepository.save(Company.builder().name("Balconi").email("Balconi@company.com").password("123456").build());
        companyRepository.save(Company.builder().name("Balocco").email("Balocco@company.com").password("123456").build());
        companyRepository.save(Company.builder().name("Barilla Group").email("Barilla.Group@company.com").password("123456").build());
        companyRepository.save(Company.builder().name("Bartolo Nardini").email("Bartolo.Nardini@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Berman's Bakery").email("Berman's.Bakery@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Bertagni").email("Bertagni@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Campari Group").email("Campari.Group@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Carapelli").email("Carapelli@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Cherkizovo").email("Cherkizovo@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Cielo").email("Cielo@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Cirio").email("Cirio@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Coppola Foods").email("Coppola.Foods@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("De Cecco").email("De.Cecco@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Domori").email("Domori@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Elledi").email("Elledi@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Ferrero SpA").email("Ferrero.SpA@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Gelati Cecchi").email("Gelati.Cecchi@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Giovanni Rana").email("Giovanni.Rana@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Girolamo Luxardo").email("Girolamo.Luxardo@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Granarolo").email("Granarolo@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Grom").email("Grom@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Guglielmo coffee").email("Guglielmo.coffee@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Gustobene").email("Gustobene@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Inmarko").email("Inmarko@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Krasny Oktyabr").email("Krasny.Oktyabr@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("La Molisana").email("La.Molisana@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Lavazza").email("Lavazza@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Lazzaroni").email("Lazzaroni@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Loacker").email("Loacker@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Marzadro Distillery").email("Marzadro.Distillery@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Massimo Zanetti Beverage Group").email("Massimo.Zanetti.Beverage.Group@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Nardini").email("Nardini@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Nonino Grappa").email("Nonino.Grappa@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Osem").email("Osem@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Parmalat").email("Parmalat@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Pernigotti").email("Pernigotti@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Polli").email("Polli@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Prigat").email("Prigat@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Puin").email("Puin@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Rot Front").email("Rot.Front@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("SaclÃ  Itali").email("SaclÃ .Italia@company.co").password("123456").build());
//        companyRepository.save(Company.builder().name("San Carlo").email("San.Carlo@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Sterilgarda").email("Sterilgarda@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Strauss").email("Strauss@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Tara").email("Tara@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Venchi").email("Venchi@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Vicenzi").email("Vicenzi@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Voiello").email("Voiello@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Wimm-Bill-Dann Foods").email("Wimm-Bill-Dann.Foods@company.com").password("123456").build());
//        companyRepository.save(Company.builder().name("Yehuda Matzos").email("Yehuda.Matzos@company.com").password("123456").build());

        //load customer
        customerRepository.save(Customer.builder().firstName("Aaron").lastName("Ahl").email("aaron.ahl@customer.com").password("123456").build());
        customerRepository.save(Customer.builder().firstName("Adam").lastName("Absalom").email("adam.absalom@customer.com").password("123456").build());
        customerRepository.save(Customer.builder().firstName("Alexander").lastName("Amsler").email("alexander.amsler@customer.com").password("123456").build());
        customerRepository.save(Customer.builder().firstName("Alicia").lastName("Anschel").email("alicia.anschel@customer.com").password("123456").build());
        customerRepository.save(Customer.builder().firstName("Allison").lastName("Anzieher").email("allison.anzieher@customer.com").password("123456").build());
        customerRepository.save(Customer.builder().firstName("Amanda").lastName("Abelson").email("amanda.abelson@customer.com").password("123456").build());
        customerRepository.save(Customer.builder().firstName("Amber").lastName("Adler").email("amber.adler@customer.com").password("123456").build());
        customerRepository.save(Customer.builder().firstName("Amy").lastName("Agosi").email("amy.agosi@customer.com").password("123456").build());
        customerRepository.save(Customer.builder().firstName("Andrea").lastName("Altmann").email("andrea.altmann@customer.com").password("123456").build());
        customerRepository.save(Customer.builder().firstName("Andrew").lastName("Fuller").email("andrew.fuller@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Andrew").lastName("Abil").email("andrew.abil@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Angela").lastName("Alpron").email("angela.alpron@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Anne").lastName("Dodsworth").email("anne.dodsworth@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Anthony").lastName("Abramsohn").email("anthony.abramsohn@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Ashley").lastName("Aaronstein").email("ashley.aaronstein@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Benjamin").lastName("Ahavah").email("benjamin.ahavah@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Bradley").lastName("Apel").email("bradley.apel@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Brandon").lastName("Abraham").email("brandon.abraham@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Brian").lastName("Abramowitz").email("brian.abramowitz@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Brittany").lastName("Admon").email("brittany.admon@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Bryan").lastName("Amdursky").email("bryan.amdursky@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Charles").lastName("Ahuvah").email("charles.ahuvah@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Christina").lastName("Adele").email("christina.adele@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Christine").lastName("Ansbach").email("christine.ansbach@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Christopher").lastName("Aaronin").email("christopher.aaronin@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Cody").lastName("Antman").email("cody.antman@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Courtney").lastName("Anixter").email("courtney.anixter@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Crystal").lastName("Agozi").email("crystal.agozi@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Daniel").lastName("Abendana").email("daniel.abendana@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Danielle").lastName("Adolescenti").email("danielle.adolescenti@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("David").lastName("Aberke").email("david.aberke@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Dustin").lastName("Alpern").email("dustin.alpern@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Elizabeth").lastName("Abravaneln").email("elizabeth.abravaneln@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Emily").lastName("Ahitov").email("emily.ahitov@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Eric").lastName("Abravanel").email("eric.abravanel@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Erica").lastName("Altschul").email("erica.altschul@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Erin").lastName("Alembik").email("erin.alembik@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Gregory").lastName("Altneu").email("gregory.altneu@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Heather").lastName("Abramson").email("heather.abramson@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Jacob").lastName("Akashia").email("jacob.akashia@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("James").lastName("Aberl").email("james.aberl@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Jamie").lastName("Algus").email("jamie.algus@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Janet").lastName("Leverling").email("janet.leverling@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Jason").lastName("Abrahm").email("jason.abrahm@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Jeffrey").lastName("Afrom").email("jeffrey.afrom@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Jennifer").lastName("Abba").email("jennifer.abba@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Jeremy").lastName("Agronsky").email("jeremy.agronsky@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Jesse").lastName("Amster").email("jesse.amster@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Jessica").lastName("Aaronsen").email("jessica.aaronsen@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("John").lastName("Aberlieb").email("john.aberlieb@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Jonathan").lastName("Abramin").email("jonathan.abramin@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Jose").lastName("Amsle").email("jose.amsle@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Joseph").lastName("Aberzuss").email("joseph.aberzuss@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Joshua").lastName("Abel").email("joshua.abel@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Justin").lastName("Abrahmsohn").email("justin.abrahmsohn@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Katherine").lastName("Altfeld").email("katherine.altfeld@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Katie").lastName("Anastasios").email("katie.anastasios@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Kelly").lastName("Aliyah").email("kelly.aliyah@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Kenneth").lastName("Amdur").email("kenneth.amdur@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Kevin").lastName("Abzug").email("kevin.abzug@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Kimberly").lastName("Africk").email("kimberly.africk@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Kristen").lastName("Amsel").email("kristen.amsel@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Kyle").lastName("Adelman").email("kyle.adelman@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Laura").lastName("Callahan").email("laura.callahan@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Laura").lastName("Adelstein").email("laura.adelstein@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Lauren").lastName("Ader").email("lauren.ader@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Lindsay").lastName("Anav").email("lindsay.anav@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Lindsey").lastName("Amschel").email("lindsey.amschel@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Lisa").lastName("Am").email("lisa.am@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Margaret").lastName("Peacock").email("margaret.peacock@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Mark").lastName("Ahikam").email("mark.ahikam@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Mary").lastName("Altschuler").email("mary.altschuler@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Matthew").lastName("Aaronson").email("matthew.aaronson@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Megan").lastName("Abudraham").email("megan.abudraham@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Melissa").lastName("Abulafia").email("melissa.abulafia@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Michael").lastName("Suyama").email("michael.suyama@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Michael").lastName("Aaron").email("michael.aaron@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Michelle").lastName("Agranat").email("michelle.agranat@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Nancy").lastName("Davolio").email("nancy.davolio@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Nathan").lastName("Alkus").email("nathan.alkus@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Nicholas").lastName("Abramsky").email("nicholas.abramsky@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Nicole").lastName("Abrams").email("nicole.abrams@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Patrick").lastName("Album").email("patrick.album@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Paul").lastName("Alpert").email("paul.alpert@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Rachel").lastName("Adelson").email("rachel.adelson@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Rebecca").lastName("Akabiah").email("rebecca.akabiah@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Richard").lastName("Adonoilom").email("richard.adonoilom@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Robert").lastName("King").email("robert.king@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Robert").lastName("Aberlein").email("robert.aberlein@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Ryan").lastName("Abrabanel").email("ryan.abrabanel@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Samantha").lastName("Alizah").email("samantha.alizah@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Sara").lastName("Alper").email("sara.alper@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Sarah").lastName("Abram").email("sarah.abram@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Scott").lastName("Alterman").email("scott.alterman@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Sean").lastName("Alcalay").email("sean.alcalay@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Shannon").lastName("Ancier").email("shannon.ancier@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Stephanie").lastName("Abramov").email("stephanie.abramov@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Stephen").lastName("Akiba").email("stephen.akiba@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Steven").lastName("Buchanan").email("steven.buchanan@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Steven").lastName("Achselrad").email("steven.achselrad@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Thomas").lastName("Ackerman").email("thomas.ackerman@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Tiffany").lastName("Agron").email("tiffany.agron@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Timothy").lastName("Acosta").email("timothy.acosta@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Travis").lastName("Altshule").email("travis.altshule@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Tyler").lastName("Alter").email("tyler.alter@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Vanessa").lastName("Andrussier").email("vanessa.andrussier@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("William").lastName("Abrami").email("william.abrami@customer.com").password("123456").build());
//        customerRepository.save(Customer.builder().firstName("Zachary").lastName("Alfandari").email("zachary.alfandari@customer.com").password("123456").build());

        //add coupons

        couponRepository.save(Coupon.builder().company(1).category(Category.ACCESSORIES).title("accessories1").description("accessories1").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2020,8,1)).amount(10).price(50).image("image").build());
        couponRepository.save(Coupon.builder().company(1).category(Category.ACCESSORIES).title("accessories2").description("accessories2").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2020,8,1)).amount(10).price(60).image("image").build());
        couponRepository.save(Coupon.builder().company(1).category(Category.ACCESSORIES).title("accessories3").description("accessories3").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2020,8,1)).amount(10).price(70).image("image").build());
        couponRepository.save(Coupon.builder().company(1).category(Category.ACCESSORIES).title("accessories4").description("accessories4").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2020,8,1)).amount(10).price(80).image("image").build());
        couponRepository.save(Coupon.builder().company(2).category(Category.ACCESSORIES).title("accessories5").description("accessories5").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2020,8,1)).amount(10).price(90).image("image").build());
        couponRepository.save(Coupon.builder().company(2).category(Category.ACCESSORIES).title("accessories6").description("accessories6").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2020,8,1)).amount(10).price(100).image("image").build());
        couponRepository.save(Coupon.builder().company(2).category(Category.ACCESSORIES).title("accessories7").description("accessories7").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(110).image("image").build());
        couponRepository.save(Coupon.builder().company(2).category(Category.ACCESSORIES).title("accessories8").description("accessories8").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(120).image("image").build());
        couponRepository.save(Coupon.builder().company(2).category(Category.ACCESSORIES).title("accessories9").description("accessories9").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(130).image("image").build());
        couponRepository.save(Coupon.builder().company(3).category(Category.BABY).title("baby1").description("baby1").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(10).image("image").build());
        couponRepository.save(Coupon.builder().company(3).category(Category.BABY).title("baby2").description("baby2").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(15).image("image").build());
        couponRepository.save(Coupon.builder().company(3).category(Category.BABY).title("baby3").description("baby3").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(20).image("image").build());
        couponRepository.save(Coupon.builder().company(7).category(Category.BABY).title("baby4").description("baby4").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(25).image("image").build());
        couponRepository.save(Coupon.builder().company(7).category(Category.BABY).title("baby5").description("baby5").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2020,8,1)).amount(10).price(30).image("image").build());
        couponRepository.save(Coupon.builder().company(7).category(Category.BABY).title("baby6").description("baby6").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2020,8,1)).amount(10).price(35).image("image").build());
        couponRepository.save(Coupon.builder().company(2).category(Category.BABY).title("baby7").description("baby7").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2020,8,1)).amount(10).price(40).image("image").build());
        couponRepository.save(Coupon.builder().company(10).category(Category.BABY).title("baby8").description("baby8").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(45).image("image").build());
        couponRepository.save(Coupon.builder().company(10).category(Category.BABY).title("baby9").description("baby9").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(50).image("image").build());
        couponRepository.save(Coupon.builder().company(10).category(Category.BABY).title("baby10").description("baby10").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(55).image("image").build());
        couponRepository.save(Coupon.builder().company(30).category(Category.GAMES).title("games1").description("games1").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2020,8,1)).amount(10).price(70).image("image").build());
        couponRepository.save(Coupon.builder().company(30).category(Category.GAMES).title("games2").description("games2").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2020,8,1)).amount(10).price(20).image("image").build());
        couponRepository.save(Coupon.builder().company(30).category(Category.GAMES).title("games3").description("games3").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2020,8,1)).amount(10).price(70).image("image").build());
        couponRepository.save(Coupon.builder().company(30).category(Category.GAMES).title("games4").description("games4").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(70).image("image").build());
        couponRepository.save(Coupon.builder().company(20).category(Category.GAMES).title("games5").description("games5").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(60).image("image").build());
        couponRepository.save(Coupon.builder().company(20).category(Category.GAMES).title("games6").description("games6").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(30).image("image").build());
        couponRepository.save(Coupon.builder().company(20).category(Category.GAMES).title("games7").description("games7").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(70).image("image").build());
        couponRepository.save(Coupon.builder().company(20).category(Category.GAMES).title("games8").description("games8").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(70).image("image").build());
        couponRepository.save(Coupon.builder().company(20).category(Category.ACCESSORIES).title("accessories7").description("accessories7").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(110).image("image").build());
        couponRepository.save(Coupon.builder().company(20).category(Category.BABY).title("baby1").description("baby1").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(10).image("image").build());
        couponRepository.save(Coupon.builder().company(20).category(Category.FOOD).title("food1").description("food1").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(10).image("image").build());
        couponRepository.save(Coupon.builder().company(12).category(Category.GAMES).title("games9").description("games9").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(70).image("image").build());
        couponRepository.save(Coupon.builder().company(12).category(Category.GAMES).title("games10").description("games10").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(70).image("image").build());
        couponRepository.save(Coupon.builder().company(15).category(Category.FOOD).title("food1").description("food1").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2020,8,1)).amount(10).price(10).image("image").build());
        couponRepository.save(Coupon.builder().company(15).category(Category.FOOD).title("food2").description("food2").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2020,8,1)).amount(10).price(10.5).image("image").build());
        couponRepository.save(Coupon.builder().company(15).category(Category.FOOD).title("food3").description("food3").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2020,8,1)).amount(10).price(11).image("image").build());
        couponRepository.save(Coupon.builder().company(15).category(Category.FOOD).title("food4").description("food4").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2020,8,1)).amount(10).price(11.5).image("image").build());
        couponRepository.save(Coupon.builder().company(15).category(Category.FOOD).title("food5").description("food5").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(12).image("image").build());
        couponRepository.save(Coupon.builder().company(15).category(Category.FOOD).title("food6").description("food6").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(12.5).image("image").build());
        couponRepository.save(Coupon.builder().company(15).category(Category.FOOD).title("food7").description("food7").startDate(LocalDate.of(2020,8,1)).endDate(LocalDate.of(2021,8,1)).amount(10).price(13).image("image").build());



    }
}
