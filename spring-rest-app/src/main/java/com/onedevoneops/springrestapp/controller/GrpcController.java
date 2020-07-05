package com.onedevoneops.springrestapp.controller;

import com.onedevoneops.springrestapp.bean.HeroNameBean;
import com.onedevoneops.springrestapp.bean.TeamFightBean;
import com.onedevoneops.springrestapp.bean.request.GetHeroNames;
import com.onedevoneops.springrestapp.bean.request.GetTeamFights;
import com.onedevoneops.springrestapp.service.FileLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author erdoganf
 */
@Service
public class GrpcController {

  private static final List<HeroNameBean> HERO_NAMES = new ArrayList<>();

  private static final List<TeamFightBean> TEAM_FIGHTS = new ArrayList<>();

  public ResponseEntity<GetHeroNames> getSmallPayload() throws IOException {
    List<HeroNameBean> heroNameBeans = FileLoader.loadJson("heronames.json", HeroNameBean.class);
    GetHeroNames body = new GetHeroNames();
    body.setHeroNames(heroNameBeans);
    return ResponseEntity.ok(body);
  }

  public ResponseEntity<GetTeamFights> getLargePayload() throws IOException {
    List<TeamFightBean> teamFightBeans = FileLoader.loadJson("teamfights.json", TeamFightBean.class);
    GetTeamFights body = new GetTeamFights();
    body.setTeamFights(teamFightBeans);
    return ResponseEntity.ok(body);
  }

  public void postSmallPayload(GetHeroNames request) {
    HERO_NAMES.addAll(request.getHeroNames());
  }

  public void postLargePayload(GetTeamFights request) {
    TEAM_FIGHTS.addAll(request.getTeamFights());
  }
}
