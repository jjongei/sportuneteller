package com.sportuenteller.olympic.manage.plyers.application.convert;

import com.sportuenteller.olympic.manage.plyers.domain.Player;

import java.io.File;
import java.util.List;

public interface WritePlayerExcel {
    File write(List<Player> players);
}
