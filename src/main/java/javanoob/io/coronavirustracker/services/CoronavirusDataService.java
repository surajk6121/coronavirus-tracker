package javanoob.io.coronavirustracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javanoob.io.coronavirustracker.models.LocationStats;

@Service
public class CoronavirusDataService {

  private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

  private List<LocationStats> allStats = new ArrayList<LocationStats>();

  public long totalReportedCases = 0;

  public long getTotalReportedCases() {
    return totalReportedCases;
  }

  public List<LocationStats> getAllStats() {
    return allStats;
  }

  @PostConstruct
  @Scheduled(cron = "* * 1 * * *")
  public void fetchVirusData() throws IOException, InterruptedException {
    List<LocationStats> newStats = new ArrayList<LocationStats>();
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();

    HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

    // System.out.println(httpResponse.body());

    StringReader csvReader = new StringReader(httpResponse.body());
    Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
    for (CSVRecord record : records) {
      // String state = record.get("Province/State");
      LocationStats locationStats = new LocationStats();
      locationStats.setState(record.get("Province/State"));
      locationStats.setCountry(record.get("Country/Region"));
      locationStats.setTotalCases(Integer.parseInt(record.get(record.size() - 1)));

      System.out.println(locationStats);
      newStats.add(locationStats);

    }

    allStats = newStats;
    totalReportedCases = allStats.stream().mapToInt(stat -> stat.getTotalCases()).sum();

  }
}
