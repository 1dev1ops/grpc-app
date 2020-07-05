package com.onedevoneops.grpcserver.service;

import com.google.protobuf.Empty;
import com.onedevoneops.grpcserver.bean.HeroNamesBean;
import com.onedevoneops.grpcserver.bean.TeamFightsBean;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author erdoganf
 */
public class PayloadTestServiceImpl extends PayloadTestServiceGrpc.PayloadTestServiceImplBase {

  @Override
  public void getSmallPayload(Empty request, StreamObserver<HeroNameResponse> responseObserver) {
    List<HeroNamesBean> heroNames;
    try {
      heroNames = FileLoader.loadJson("heronames.json", HeroNamesBean.class);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    List<HeroName> heroNamesProto =
        heroNames.stream()
                 .map(hero -> HeroName.newBuilder().setHeroId(hero.getHeroId())
                                      .setName(hero.getName())
                                      .setLocalizedName(hero.getLocalizedName())
                                      .build())
                 .collect(Collectors.toList());

    responseObserver.onNext(HeroNameResponse.newBuilder().addAllHeroNames(heroNamesProto).build());
    responseObserver.onCompleted();
  }

  @Override
  public void getLargePayload(Empty request, StreamObserver<TeamFightResponse> responseObserver) {
    List<TeamFightsBean> teamFights;
    try {
      teamFights = FileLoader.loadJson("teamfights.json", TeamFightsBean.class);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    List<TeamFight> teamFightsProto =
        teamFights.stream()
                  .map(fight -> TeamFight.newBuilder()
                                         .setDeaths(fight.getDeaths())
                                         .setLastDeath(fight.getLastDeath())
                                         .setMatchId(fight.getMatchId())
                                         .setStart(fight.getStart())
                                         .setEnd(fight.getEnd())
                                         .build())
                  .collect(Collectors.toList());

    responseObserver.onNext(TeamFightResponse.newBuilder().addAllTeamFights(teamFightsProto).build());
    responseObserver.onCompleted();
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
