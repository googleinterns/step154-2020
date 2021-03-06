// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.data;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.GeoPt;
import java.util.Objects;

/** A class for the location information of a business. */
public final class LocationData {
  private final long id;
  private GeoPt salePoint;
  private String geoHash;
  private float radius;

  public LocationData(long id, GeoPt salePoint, String geoHash, float radius) {
    this.id = id;
    this.salePoint = salePoint;
    this.geoHash = geoHash;
    this.radius = radius;
  }

  public LocationData(EmbeddedEntity embeddedLocation) {
    if (embeddedLocation == null) {
      throw new IllegalArgumentException("LocationData cannot be initialized with null EmbeddedEntity");
    }

    this.id = (long) embeddedLocation.getKey().getId();
    this.salePoint = (GeoPt) embeddedLocation.getProperty("salePoint");
    this.geoHash = (String) embeddedLocation.getProperty("geoHash");
    this.radius = ((Double) embeddedLocation.getProperty("radius")).floatValue();
  }

  public long getId() {
    return id;
  }

  public GeoPt getSalePoint() {
    return salePoint;
  }

  public String getGeoHash() {
    return geoHash;
  }

  public float getRadius() {
    return radius;
  }

  public void setSalePoint(GeoPt salePoint) {
    this.salePoint = salePoint;
  }

  public void setGeoHash(String geoHash) {
    this.geoHash = geoHash;
  }

  public void setRadius(float radius) {
    this.radius = radius;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (!(obj instanceof LocationData)) {
      return false;
    }
    LocationData location = (LocationData) obj;
    return id == location.id && Objects.equals(salePoint, location.salePoint) && 
        Objects.equals(geoHash, location.geoHash) && radius == location.radius;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, salePoint, geoHash, radius);
  }
}