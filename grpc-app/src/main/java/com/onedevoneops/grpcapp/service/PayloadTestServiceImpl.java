package com.onedevoneops.grpcapp.service;

import com.google.protobuf.Empty;
import com.onedevoneops.grpc.service.HeroName;
import com.onedevoneops.grpc.service.HeroNameResponse;
import com.onedevoneops.grpc.service.PayloadTestServiceGrpc.PayloadTestServiceImplBase;
import com.onedevoneops.grpc.service.TeamFight;
import com.onedevoneops.grpc.service.TeamFightResponse;
import com.onedevoneops.grpcapp.bean.HeroNamesBean;
import com.onedevoneops.grpcapp.bean.TeamFightsBean;
import com.onedevoneops.grpcapp.util.FileLoader;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author erdoganf
 */
public class PayloadTestServiceImpl extends PayloadTestServiceImplBase {

  @Override
  public void getSmallPayload(Empty request, StreamObserver<HeroNameResponse> responseObserver) {
    List<HeroName> heroNamesProto = getHeroNamesProto();

    responseObserver.onNext(HeroNameResponse.newBuilder().addAllHeroNames(heroNamesProto).build());
    responseObserver.onCompleted();
  }

  public static List<HeroName> getHeroNamesProto() {
    List<HeroNamesBean> heroNames;
    try {
      heroNames = FileLoader.loadJson("heronames.json", HeroNamesBean.class);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return heroNames.stream()
                    .map(hero -> HeroName.newBuilder().setHeroId(hero.getHeroId())
                                         .setName(hero.getName())
                                         .setLocalizedName(hero.getLocalizedName())
                                         .build())
                    .collect(Collectors.toList());
  }

  @Override
  public void getLargePayload(Empty request, StreamObserver<TeamFightResponse> responseObserver) {
    List<TeamFight> teamFightsProto = getTeamFightsProto();

    responseObserver.onNext(TeamFightResponse.newBuilder().addAllTeamFights(teamFightsProto).build());
    responseObserver.onCompleted();
  }

  public static List<TeamFight> getTeamFightsProto() {
    List<TeamFightsBean> teamFights;
    try {
      teamFights = FileLoader.loadJson("teamfights.json", TeamFightsBean.class);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return teamFights.stream()
                     .map(fight -> TeamFight.newBuilder()
                                            .setDeaths(fight.getDeaths())
                                            .setLastDeath(fight.getLastDeath())
                                            .setMatchId(fight.getMatchId())
                                            .setStart(fight.getStart())
                                            .setEnd(fight.getEnd())
                                            .build())
                     .collect(Collectors.toList());
  }

  @Override
  public void postSmallPayload(HeroNameResponse request, StreamObserver<Empty> responseObserver) {
    responseObserver.onNext(Empty.newBuilder().build());
    responseObserver.onCompleted();
  }

  @Override
  public void postLargePayload(TeamFightResponse request, StreamObserver<Empty> responseObserver) {
    responseObserver.onNext(Empty.newBuilder().build());
    responseObserver.onCompleted();
  }
}
