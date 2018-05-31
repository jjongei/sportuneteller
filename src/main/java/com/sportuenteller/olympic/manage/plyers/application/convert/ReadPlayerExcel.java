package com.sportuenteller.olympic.manage.plyers.application.convert;

import com.sportuenteller.olympic.manage.plyers.domain.Player;

import java.io.File;
import java.util.List;

public interface ReadPlayerExcel {
    List<Player> read(File excel, List<Player> players);
}
