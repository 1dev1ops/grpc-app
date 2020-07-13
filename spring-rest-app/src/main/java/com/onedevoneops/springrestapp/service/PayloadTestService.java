package com.onedevoneops.springrestapp.service;

import com.onedevoneops.springrestapp.bean.HeroNameBean;
import com.onedevoneops.springrestapp.bean.TeamFightBean;
import com.onedevoneops.springrestapp.bean.request.GetHeroNames;
import com.onedevoneops.springrestapp.bean.request.GetTeamFights;
import com.onedevoneops.springrestapp.util.FileLoader;
import java.io.IOException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author erdoganf
 */
@Service
public class PayloadTestService {

  public ResponseEntity<GetHeroNames> getSmallPayload() throws IOException {
    GetHeroNames body = getHeroNamesRequest();
    return ResponseEntity.ok(body);
  }

  public static GetHeroNames getHeroNamesRequest() throws IOException {
    List<HeroNameBean> heroNameBeans = FileLoader.loadJson("heronames.json", HeroNameBean.class);
    GetHeroNames body = new GetHeroNames();
    body.setHeroNames(heroNameBeans);
    return body;
  }

  public ResponseEntity<GetTeamFights> getLargePayload() throws IOException {
    GetTeamFights body = getTeamFightsRequest();
    return ResponseEntity.ok(body);
  }

  public static GetTeamFights getTeamFightsRequest() throws IOException {
    List<TeamFightBean> teamFightBeans = FileLoader.loadJson("teamfights.json", TeamFightBean.class);
    GetTeamFights body = new GetTeamFights();
    body.setTeamFights(teamFightBeans);
    return body;
  }

  public void postSmallPayload(GetHeroNames request) {
    return;
  }

  public void postLargePayload(GetTeamFights request) {
    return;
  }
}
