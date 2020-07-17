package com.example.carrierapp.repositories;

import com.couchbase.client.core.error.CouchbaseException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.search.SearchQuery;
import com.couchbase.client.java.search.result.SearchResult;
import com.couchbase.client.java.search.result.SearchRow;
import com.example.carrierapp.entity.DailyCourierTrackingInfo;
import com.example.carrierapp.entity.Store;
import org.springframework.data.couchbase.core.query.N1QLExpression;
import org.springframework.data.couchbase.repository.query.support.N1qlUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourierLocationRepo {
    private final String STORE_BUCKET_NAME= "CourierLocation";
    private final String TRACK_INFO_BUCKET_NAME= "CourierLocationInfo";

    private final Cluster cluster;

    public CourierLocationRepo(Cluster cluster) {
        this.cluster = cluster;
    }

    public boolean isEnteredBefore(long courierId, String storeId, long timestamp) {

        N1QLExpression query = N1qlUtils.createSelectClauseForEntity(STORE_BUCKET_NAME)
                .from("DailyCourierInfo")
                .where(N1QLExpression.s("courierId=" + courierId))
                .and(N1QLExpression.s("storeId=" + storeId))
                .convertToString();

        return true;
    }

    public List<Store> findNearestStores(double latitude, double longitude) {
        List<Store> stores = new ArrayList<>();
        try {
            final SearchResult result = cluster
                    .searchQuery("geoLocationIndex", SearchQuery.geoDistance(longitude, latitude, "100m"));

            for (SearchRow row : result.rows()) {
                System.out.println("Found row: " + row.id());

                GetResult courierLocation = cluster.bucket(STORE_BUCKET_NAME).defaultCollection().get(row.id());

                stores.add(courierLocation.contentAs(Store.class));
            }

            return stores;
        } catch (CouchbaseException ex) {
            ex.printStackTrace();
            return stores;
        }
    }

    public void insertCourierTrackingInfo(DailyCourierTrackingInfo dailyCourierTrackingInfo) {
        cluster.bucket(TRACK_INFO_BUCKET_NAME).defaultCollection().insert(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)), dailyCourierTrackingInfo);
    }
}
