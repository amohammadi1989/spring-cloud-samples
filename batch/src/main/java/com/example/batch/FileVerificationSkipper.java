package com.example.batch;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

import java.io.FileNotFoundException;
/**
 * Created By: Ali Mohammadi
 * Date: 01 Dec, 2021
 */
public class FileVerificationSkipper implements SkipPolicy {
  @Override
  public boolean shouldSkip(Throwable exception, int skipCount) throws SkipLimitExceededException {
    System.out.println( "message===>"+exception.getMessage());
      return true;
    }
}
