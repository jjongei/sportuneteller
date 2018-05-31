package com.sportuenteller.olympic.games.game.application.convert;

import com.sportuenteller.olympic.games.game.domain.Game;

import java.io.File;
import java.util.List;

public interface WriteGameExcel {
    File write(List<Game> games);
}
