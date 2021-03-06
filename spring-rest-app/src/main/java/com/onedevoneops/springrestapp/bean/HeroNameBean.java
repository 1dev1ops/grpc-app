package com.onedevoneops.springrestapp.bean;

/**
 * @author erdoganf
 */

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class HeroNameBean {

  private String name;

  private Integer heroId;

  private String localizedName;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getHeroId() {
    return heroId;
  }

  public void setHeroId(Integer heroId) {
    this.heroId = heroId;
  }

  public String getLocalizedName() {
    return localizedName;
  }

  public void setLocalizedName(String localizedName) {
    this.localizedName = localizedName;
  }

  @Override
  public String toString() {
    return "HeroNamesBean{" +
           "name='" + name + '\'' +
           ", heroId=" + heroId +
           ", localizedName='" + localizedName + '\'' +
           '}';
  }
}
