package ru.nsu.yakovleva.notprime;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * The PerformanceTest class generates and saves a performance comparison chart
 * for prime number search algorithms using JFreeChart library.
 */
public class PerformanceTest {
    private final JFreeChart chart;

    /**
     * Constructs a PerformanceTest object with the specified dataset.
     *
     * @param dataset The dataset containing performance data for algorithms.
     */
    public PerformanceTest(XYSeriesCollection dataset) {
        chart = createChart(dataset);
    }

    /**
     * Creates a JPEG file representing the performance chart and saves it to disk.
     *
     * @throws IOException If an I/O error occurs during file creation or saving.
     */
    public void createFile() throws IOException {
        File file = new File("performance.jpg");
        if (file.exists()) {
            file.delete();
        }
        ChartUtils.saveChartAsJPEG(file, chart, 500, 500);
    }

    /**
     * Creates and configures a JFreeChart object for the performance chart.
     *
     * @param dataset The dataset containing performance data for algorithms.
     * @return The configured JFreeChart object.
     */
    private JFreeChart createChart(XYSeriesCollection dataset) {
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Performance Comparison of Prime Number Search Algorithms",
                "Array Size",
                "Time",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );

        XYPlot plot = chart.getXYPlot();
        plot.setAxisOffset(new RectangleInsets(1, 1, 1, 1));

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
        for (int i = 0; i < dataset.getSeriesCount(); i++) {
            renderer.setSeriesStroke(i, new BasicStroke(2.0f));
        }

        plot.setRenderer(renderer);
        ValueAxis axis = plot.getDomainAxis();
        axis.setAxisLineVisible(false);

        return chart;
    }
}