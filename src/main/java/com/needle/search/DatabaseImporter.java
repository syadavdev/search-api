package com.needle.search;


import com.needle.search.respository.TvShow;
import com.needle.search.respository.TvShowRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * When Application first time boot up this class insert csv file to target database
 * When Application second time boot up it gives you error in console of duplication entry
 * But application run's fine
 *
 */
@Component
public class DatabaseImporter implements ApplicationRunner{

    @Autowired
    private TvShowRepository repository;

    @Override
    public void run(ApplicationArguments args) {

        try(InputStream is = getClass().getClassLoader().getResourceAsStream("netflix_titles.csv");
            InputStreamReader reader = new InputStreamReader(is)) {
            final List<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader).getRecords();
            List<TvShow> shows = new ArrayList();
            for(CSVRecord record : records){
                shows.add(TvShow.builder()
                        .showId(record.get(0))
                        .type(record.get(1))
                        .title(record.get(2))
                        .director(record.get(3))
                        .cast(record.get(4))
                        .country(record.get(5))
                        .dateAdded(record.get(6))
                        .releaseYear(Integer.parseInt(record.get(7)))
                        .rating(record.get(8))
                        .duration(record.get(9))
                        .build());
            }

            repository.saveAll(shows);
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

}
