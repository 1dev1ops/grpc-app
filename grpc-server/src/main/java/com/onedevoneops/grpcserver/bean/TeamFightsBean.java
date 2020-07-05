package com.onedevoneops.grpcserver.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author erdoganf
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TeamFightsBean {

  private Integer matchId;

  private Integer start;

  private Integer end;

  private Integer lastDeath;

  private Integer deaths;

  public Integer getMatchId() {
    return matchId;
  }

  public void setMatchId(Integer matchId) {
    this.matchId = matchId;
  }

  public Integer getStart() {
    return start;
  }

  public void setStart(Integer start) {
    this.start = start;
  }

  public Integer getEnd() {
    return end;
  }

  public void setEnd(Integer end) {
    this.end = end;
  }

  public Integer getLastDeath() {
    return lastDeath;
  }

  public void setLastDeath(Integer lastDeath) {
    this.lastDeath = lastDeath;
  }

  public Integer getDeaths() {
    return deaths;
  }

  public void setDeaths(Integer deaths) {
    this.deaths = deaths;
  }

  @Override
  public String toString() {
    return "TeamFightsBean{" +
           "match_id=" + matchId +
           ", start=" + start +
           ", end=" + end +
           ", last_death=" + lastDeath +
           ", deaths=" + deaths +
           '}';
  }
}
