package com.onedevoneops.springrestapp.bean.request;

import com.onedevoneops.springrestapp.bean.TeamFightBean;
import java.util.List;

/**
 * @author erdoganf
 */
public class GetTeamFights {

  private List<TeamFightBean> teamFights;

  public List<TeamFightBean> getTeamFights() {
    return teamFights;
  }

  public void setTeamFights(List<TeamFightBean> teamFights) {
    this.teamFights = teamFights;
  }

  @Override
  public String toString() {
    return "GetTeamFights{" +
           "teamFights=" + teamFights +
           '}';
  }
}
