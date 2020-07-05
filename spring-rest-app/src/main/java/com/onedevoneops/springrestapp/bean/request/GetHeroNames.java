package com.onedevoneops.springrestapp.bean.request;

import com.onedevoneops.springrestapp.bean.HeroNameBean;
import java.util.List;

/**
 * @author erdoganf
 */
public class GetHeroNames {

  private List<HeroNameBean> heroNames;

  public List<HeroNameBean> getHeroNames() {
    return heroNames;
  }

  public void setHeroNames(List<HeroNameBean> heroNames) {
    this.heroNames = heroNames;
  }

  @Override
  public String toString() {
    return "GetHeroNames{" +
           "heroNames=" + heroNames +
           '}';
  }
}
