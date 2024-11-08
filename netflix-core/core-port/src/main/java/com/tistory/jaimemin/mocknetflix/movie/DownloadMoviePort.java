package com.tistory.jaimemin.mocknetflix.movie;

public interface DownloadMoviePort {

	void save(UserMovieDownload domain);

	long downloadCntToday(String userId);
}
